package fuzs.mutantmonsters.common.world.level;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.mutantmonsters.common.world.entity.ai.goal.TrackSummonerGoal;
import fuzs.mutantmonsters.common.world.entity.mutant.MutantZombie;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.scores.Scoreboard;

import java.util.List;

public class ZombieResurrection extends BlockPos {
    public static final Codec<ZombieResurrection> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockPos.CODEC.fieldOf(
                            "block_pos").forGetter(ZombieResurrection::getBlockPos),
                    Codec.INT.fieldOf("current_tick").forGetter(ZombieResurrection::getCurrentTick))
            .apply(instance, ZombieResurrection::new));
    public static final Codec<List<ZombieResurrection>> LIST_CODEC = CODEC.listOf();

    private int tick;

    public ZombieResurrection(Level level, int x, int y, int z) {
        super(x, y, z);
        this.tick = 100 + level.getRandom().nextInt(40);
    }

    public ZombieResurrection(BlockPos pos, int tick) {
        super(pos);
        this.tick = tick;
    }

    public static int getSuitableGround(Level world, int x, int y, int z) {
        return getSuitableGround(world, x, y, z, 4, true);
    }

    public static int getSuitableGround(Level level, int x, int y, int z, int range, boolean checkDay) {
        int i = y;

        while (true) {
            if (Math.abs(y - i) > range) {
                return -1;
            }

            BlockPos startPos = new BlockPos(x, i, z);
            BlockPos posUp = startPos.above();
            BlockState blockState = level.getBlockState(startPos);
            if (blockState.is(BlockTags.FIRE)) {
                return -1;
            }

            if ((!checkDay || level.getFluidState(startPos).is(FluidTags.LAVA)) && !level.getFluidState(startPos)
                    .isEmpty()) {
                break;
            }

            if (level.isEmptyBlock(startPos)) {
                --i;
            } else {
                if (!level.isEmptyBlock(startPos) && level.isEmptyBlock(posUp) && blockState.getCollisionShape(level,
                        startPos).isEmpty()) {
                    --i;
                    break;
                }

                if (level.isEmptyBlock(startPos) || level.isEmptyBlock(posUp) || level.getBlockState(posUp)
                        .getCollisionShape(level, posUp)
                        .isEmpty()) {
                    break;
                }

                ++i;
            }
        }

        if (checkDay && level.isBrightOutside()) {
            BlockPos lightPos = new BlockPos(x, y + 1, z);
            float f = level.getPathfindingCostFromLightLevels(lightPos);
            if (f > 0.0F && level.canSeeSkyFromBelowWater(lightPos) && level.getRandom().nextInt(3) != 0) {
                return -1;
            }
        }

        return i;
    }

    public static EntityType<? extends Zombie> getZombieByLocation(Level level, BlockPos pos) {
        if ((level.getBiome(pos).is(BiomeTags.IS_OCEAN) || level.getBiome(pos).is(BiomeTags.IS_RIVER))
                && level.isWaterAt(pos)) {
            return EntityTypes.DROWNED;
        } else if (level.isBrightOutside() && level.canSeeSky(pos)) {
            return EntityTypes.HUSK;
        } else {
            return level.getRandom().nextFloat() < 0.05F ? EntityTypes.ZOMBIE_VILLAGER : EntityTypes.ZOMBIE;
        }
    }

    public int getCurrentTick() {
        return this.tick;
    }

    private BlockPos getBlockPos() {
        return this;
    }

    public boolean update(MutantZombie mutantZombie) {
        Level level = mutantZombie.level();
        BlockPos abovePos = this.above();
        if (!level.isEmptyBlock(this) && level.isEmptyBlock(abovePos)) {
            if (mutantZombie.getRandom().nextInt(15) == 0) {
                level.levelEvent(2001, abovePos, Block.getId(level.getBlockState(this)));
            }

            if (--this.tick <= 0) {
                Zombie zombie = getZombieByLocation(level, abovePos).create(level, EntitySpawnReason.MOB_SUMMONED);
                if (zombie == null) {
                    return false;
                }

                if (level instanceof ServerLevel serverLevel) {
                    zombie.goalSelector.addGoal(0, new TrackSummonerGoal(zombie, mutantZombie));
                    zombie.goalSelector.addGoal(3, new MoveTowardsRestrictionGoal(zombie, 1.0));
                    zombie.setHealth(zombie.getMaxHealth() * (0.6F + 0.4F * zombie.getRandom().nextFloat()));
                    zombie.snapTo(abovePos, mutantZombie.getYRot(), 0.0F);
                    zombie.finalizeSpawn(serverLevel,
                            serverLevel.getCurrentDifficultyAt(this),
                            EntitySpawnReason.MOB_SUMMONED,
                            null);
                    zombie.playAmbientSound();
                    serverLevel.addFreshEntity(zombie);
                }

                level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, abovePos, Block.getId(level.getBlockState(this)));
                if (mutantZombie.getTeam() != null) {
                    Scoreboard scoreboard = level.getScoreboard();
                    scoreboard.addPlayerToTeam(zombie.getScoreboardName(),
                            scoreboard.getPlayerTeam(mutantZombie.getTeam().getName()));
                }

                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
