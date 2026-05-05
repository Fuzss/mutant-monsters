package fuzs.mutantmonsters.common.world.level;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.mutantmonsters.common.init.ModRegistry;
import fuzs.mutantmonsters.common.network.ClientboundSeismicWaveFluidParticlesMessage;
import fuzs.mutantmonsters.common.services.CommonAbstractions;
import fuzs.puzzleslib.common.api.network.v4.MessageSender;
import fuzs.puzzleslib.common.api.network.v4.PlayerSet;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class SeismicWave extends BlockPos {
    private static final int MAX_SEISMIC_WAVES_PER_PLAYER = 64;
    public static final Codec<SeismicWave> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockPos.CODEC.fieldOf(
                            "block_pos").forGetter(SeismicWave::getBlockPos),
                    Codec.BOOL.fieldOf("initial").forGetter(SeismicWave::isInitial),
                    Codec.BOOL.fieldOf("affects_terrain").forGetter(SeismicWave::affectsTerrain))
            .apply(instance, SeismicWave::new));
    public static final Codec<List<SeismicWave>> LIST_CODEC = CODEC.listOf();

    private final boolean initial;
    private final boolean affectsTerrain;

    public SeismicWave(BlockPos blockPos, boolean initial, boolean affectsTerrain) {
        this(blockPos.getX(), blockPos.getY(), blockPos.getZ(), initial, affectsTerrain);
    }

    public SeismicWave(int x, int y, int z, boolean initial, boolean affectsTerrain) {
        super(x, y, z);
        this.initial = initial;
        this.affectsTerrain = affectsTerrain;
    }

    private BlockPos getBlockPos() {
        return this;
    }

    public boolean isInitial() {
        return this.initial;
    }

    public boolean affectsTerrain() {
        return this.affectsTerrain;
    }

    public static void createWaves(Level world, List<SeismicWave> list, int x1, int z1, int x2, int z2, int y) {
        int deltaX = x2 - x1;
        int deltaZ = z2 - z1;
        int xStep = deltaX < 0 ? -1 : 1;
        int zStep = deltaZ < 0 ? -1 : 1;
        deltaX = Math.abs(deltaX);
        deltaZ = Math.abs(deltaZ);
        int x = x1;
        int z = z1;
        int deltaX2 = deltaX * 2;
        int deltaZ2 = deltaZ * 2;
        int firstY = ZombieResurrection.getSuitableGround(world, x1, y, z1, 3, false);
        SeismicWave wave = new SeismicWave(x1, y, z1, true, true);
        if (firstY != -1) {
            wave = new SeismicWave(x1, firstY, z1, true, true);
        }

        list.add(wave);
        int error;
        int i;
        if (deltaX2 >= deltaZ2) {
            error = deltaX;

            for (i = 0; i < deltaX; ++i) {
                x += xStep;
                error += deltaZ2;
                if (error > deltaX2) {
                    z += zStep;
                    error -= deltaX2;
                }

                addWave(world, list, x, y, z);
            }
        } else {
            error = deltaZ;

            for (i = 0; i < deltaZ; ++i) {
                z += zStep;
                error += deltaX2;
                if (error > deltaZ2) {
                    x += xStep;
                    error -= deltaZ2;
                }

                addWave(world, list, x, y, z);
            }
        }

    }

    @Nullable
    public static SeismicWave addWave(Level level, List<SeismicWave> list, int x, int y, int z) {
        y = ZombieResurrection.getSuitableGround(level, x, y, z, 3, false);
        SeismicWave wave = null;
        if (y != -1) {
            list.add(wave = new SeismicWave(x, y, z, false, true));
        }

        if (level.getRandom().nextInt(2) == 0) {
            list.add(new SeismicWave(x, y + 1, z, false, false));
        }

        return wave;
    }

    public void affectBlocks(ServerLevel serverLevel, Entity entity) {
        if (this.affectsTerrain) {
            BlockPos posAbove = this.above();
            BlockState blockstate = serverLevel.getBlockState(this);
            Block block = blockstate.getBlock();
            Player playerEntity = entity instanceof Player ? (Player) entity : null;
            if (playerEntity != null && playerEntity.mayBuild() || serverLevel.getGameRules()
                    .get(GameRules.MOB_GRIEFING)) {
                if (blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(Blocks.DIRT_PATH)
                        || blockstate.is(Blocks.FARMLAND) || blockstate.is(Blocks.PODZOL)
                        || blockstate.is(Blocks.MYCELIUM)) {
                    serverLevel.setBlockAndUpdate(this, Blocks.DIRT.defaultBlockState());
                }

                BlockState blockAbove = serverLevel.getBlockState(posAbove);
                float hardness = blockAbove.getDestroySpeed(serverLevel, posAbove);
                if (blockAbove.getCollisionShape(serverLevel, posAbove).isEmpty() && hardness > -1.0F
                        && hardness <= 1.0F) {
                    serverLevel.destroyBlock(posAbove, playerEntity != null);
                }

                if (block instanceof DoorBlock) {
                    if (DoorBlock.isWoodenDoor(blockstate)) {
                        serverLevel.levelEvent(LevelEvent.SOUND_ZOMBIE_WOODEN_DOOR, this, 0);
                    } else {
                        serverLevel.levelEvent(LevelEvent.SOUND_ZOMBIE_IRON_DOOR, this, 0);
                    }
                }

                if (block instanceof TntBlock) {
                    CommonAbstractions.INSTANCE.onBlockCaughtFire(block,
                            blockstate,
                            serverLevel,
                            this,
                            null,
                            playerEntity);
                    serverLevel.removeBlock(this, false);
                }
            }

            if (block instanceof BellBlock) {
                ((BellBlock) block).onHit(serverLevel,
                        blockstate,
                        new BlockHitResult(Vec3.atLowerCornerOf(this), entity.getDirection(), this, false),
                        playerEntity,
                        true);
            }

            if (blockstate.is(Blocks.REDSTONE_ORE)) {
                block.stepOn(serverLevel, this, blockstate, entity);
            }

            if (blockstate.getFluidState().isEmpty()) {
                serverLevel.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, posAbove, Block.getId(blockstate));
            } else {
                PlayerSet playerSet = PlayerSet.nearPosition(null,
                        this.getX() + 0.5,
                        this.getY(),
                        this.getZ() + 0.5,
                        1024.0,
                        (ServerLevel) entity.level());
                MessageSender.broadcast(playerSet, new ClientboundSeismicWaveFluidParticlesMessage(this));
            }
        }
    }

    public static void addAll(Player player, Collection<SeismicWave> seismicWaves) {
        ImmutableList.Builder<SeismicWave> builder = ImmutableList.builder();
        builder.addAll(ModRegistry.SEISMIC_WAVE_ATTACHMENT_TYPE.get(player));
        builder.addAll(seismicWaves);
        ModRegistry.SEISMIC_WAVE_ATTACHMENT_TYPE.set(player, builder.build());
    }

    public static SeismicWave poll(Player player) {
        List<SeismicWave> seismicWaves = ModRegistry.SEISMIC_WAVE_ATTACHMENT_TYPE.get(player);
        if (seismicWaves.size() > MAX_SEISMIC_WAVES_PER_PLAYER) {
            seismicWaves = seismicWaves.subList(seismicWaves.size() - MAX_SEISMIC_WAVES_PER_PLAYER,
                    seismicWaves.size());
        }

        if (!seismicWaves.isEmpty()) {
            SeismicWave seismicWave = seismicWaves.getFirst();
            seismicWaves = seismicWaves.subList(1, seismicWaves.size());
            ModRegistry.SEISMIC_WAVE_ATTACHMENT_TYPE.set(player, ImmutableList.copyOf(seismicWaves));
            return seismicWave;
        } else {
            return null;
        }
    }
}
