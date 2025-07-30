package net.oni_feralis.oceanmods.entity;

import net.minecraft.nbt.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.InteractGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.oni_feralis.oceanmods.entity.ai.goal.TradeWithPlayerGoal;
import net.oni_feralis.oceanmods.inventory.SeaVillagerMenu;
import net.oni_feralis.oceanmods.trades.SeaVillagerOffers;
import net.oni_feralis.oceanmods.trades.type.BaseTrade;
import net.oni_feralis.oceanmods.util.Utils;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.*;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractSeaVillagerEntity extends TraderSeaCreatureEntity implements Npc
{
    private @Nullable Player customer;
    private @Nullable MerchantOffers offers;
    private final Set<UUID> tradedCustomers = new HashSet<>();

    private int despawnDelay = -1;
    private int fallCounter;
    private int restockDelay;

    protected AbstractSeaVillagerEntity(EntityType<? extends TraderSeaCreatureEntity> type, Level level)
    {
        super(type, level);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(12, new InteractGoal(this, Player.class, 4.0F, 1.0F));
        this.goalSelector.addGoal(13, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    public abstract ResourceLocation getTexture();

    public int getFallCounter()
    {
        return this.fallCounter;
    }


    @Override
    public void baseTick()
    {
        if(this.despawnDelay > 0)
        {
            this.despawnDelay--;
        }

        super.baseTick();
        this.updateSwingTime(); //TODO test
        if(!this.level().isClientSide() && this.getMaxRestockDelay() != -1)
        {
            if(++this.restockDelay >= this.getMaxRestockDelay())
            {
                this.getOffers().forEach(MerchantOffer::resetUses);
                this.restockDelay = 0;
                this.resendOffers();
            }
        }
    }

    private void resendOffers()
    {
        MerchantOffers offers = this.getOffers();
        Player player = this.getTradingPlayer();
        if(player != null && !offers.isEmpty())
        {
            player.sendMerchantOffers(player.containerMenu.containerId, offers, 0, 0, false, this.canRestock());
        }
    }

    @Override
    public void setTradingPlayer(@Nullable Player player)
    {
        this.customer = player;
    }

    @Nullable
    @Override
    public Player getTradingPlayer()
    {
        return this.customer;
    }

    public boolean hasCustomer()
    {
        return this.customer != null;
    }

    @Override
    public MerchantOffers getOffers()
    {
        if(this.offers == null)
        {
            this.offers = new SeaVillagerOffers();
            this.populateTradeData();
        }
        return this.offers;
    }

    protected abstract void populateTradeData();

    protected void addTrades(MerchantOffers offers, @Nullable List<BaseTrade> trades, int max, boolean shuffle)
    {
        if(trades == null)
            return;
        List<Integer> randomIndexes = IntStream.range(0, trades.size()).boxed().collect(Collectors.toList());
        if(shuffle) Collections.shuffle(randomIndexes);
        randomIndexes = randomIndexes.subList(0, Math.min(trades.size(), max));
        for(Integer index : randomIndexes)
        {
            BaseTrade trade = trades.get(index);
            MerchantOffer offer = trade.createVanillaOffer(this, this.getRandom());
            if(offer != null)
            {
                offers.add(offer);
            }
        }
    }

    @Override
    public void openTradingScreen(Player player, Component title, int level)
    {
        OptionalInt id = player.openMenu(new SimpleMenuProvider((windowId, playerInventory, player1) -> {
            return new SeaVillagerMenu(windowId, playerInventory, this);
        }, title));
        if(id.isPresent())
        {
            MerchantOffers offers = this.getOffers();
            if(!offers.isEmpty())
            {
                player.sendMerchantOffers(id.getAsInt(), offers, level, 0, false, this.canRestock());
            }
        }
    }

    @Override
    public boolean canRestock()
    {
        return true;
    }

    @Override
    public void overrideOffers(@Nullable MerchantOffers offers) {}

    @Override
    public void notifyTrade(MerchantOffer offer)
    {
        offer.increaseUses();
        if(this.customer != null)
        {
            this.tradedCustomers.add(this.customer.getUUID());
        }
        if(this.level() instanceof ServerLevel serverLevel)
        {
            ExperienceOrb.award(serverLevel, this.getPosition(1F), offer.getXp());
        }
    }

    @Override
    public void notifyTradeUpdated(ItemStack stack)
    {

    }

    @Override
    public boolean isClientSide()
    {
        return this.level().isClientSide();
    }

    @Override
    public int getVillagerXp()
    {
        return 0;
    }

    @Override
    public void overrideXp(int xpIn) {}

    @Override
    public boolean showProgressBar()
    {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound()
    {
        return SoundEvents.VILLAGER_YES;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand)
    {
        ItemStack heldItem = player.getItemInHand(hand);
        if(heldItem.getItem() == Items.NAME_TAG)
        {
            InteractionResult result = heldItem.interactLivingEntity(player, this, hand);
            if(result.consumesAction())
            {
                // Remove the wandering restriction once named
                this.clearRestriction();
            }
            return result;
        }
        else if(this.isAlive() && !this.hasCustomer() && !this.isBaby() && (this.fireImmune() || !this.isOnFire()) && !this.isStunned()) //TODO check for egg
        {
            if(this.getOffers().isEmpty())
            {
                return InteractionResult.sidedSuccess(this.isClientSide());
            }
            else if(!this.isClientSide() && (this.getLastHurtByMob() == null || this.getLastHurtByMob() != player))
            {
                this.setTradingPlayer(player);
                this.openTradingScreen(player, Objects.requireNonNull(this.getDisplayName()), 1);
            }
            return InteractionResult.sidedSuccess(this.isClientSide());
        }
        return super.mobInteract(player, hand);
    }

    public void setDespawnDelay(int despawnDelay)
    {
        this.despawnDelay = despawnDelay;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        if(compound.contains("Offers", 10))
        {
            this.offers = new SeaVillagerOffers(compound.getCompound("Offers"));
        }
        if(compound.contains("DespawnDelay", Tag.TAG_INT))
        {
            this.despawnDelay = compound.getInt("DespawnDelay");
        }
        if(compound.contains("RestockDelay", Tag.TAG_INT))
        {
            this.restockDelay = compound.getInt("RestockDelay");
        }
        if(compound.contains("TradedCustomers", Tag.TAG_LIST))
        {
            this.tradedCustomers.clear();
            ListTag list = compound.getList("TradedCustomers", Tag.TAG_STRING);
            list.forEach(tag -> {
                if(tag instanceof StringTag s) {
                    UUID id = Utils.parseUuid(s.getAsString());
                    if(id != null) {
                        this.tradedCustomers.add(id);
                    }
                }
            });
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        MerchantOffers offers = this.getOffers();
        if(!offers.isEmpty())
        {
            MerchantOffers.CODEC.encodeStart(NbtOps.INSTANCE, offers).result()
                    .ifPresent(tag -> {
                        compound.put("Offers", tag);
                    });
        }
        compound.putInt("DespawnDelay", this.despawnDelay);
        compound.putInt("RestockDelay", this.restockDelay);

        if(!this.tradedCustomers.isEmpty())
        {
            ListTag list = new ListTag();
            this.tradedCustomers.forEach(id -> {
                list.add(StringTag.valueOf(id.toString()));
            });
            compound.put("TradedCustomers", list);
        }
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return SoundEvents.VILLAGER_HURT;
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 20F).add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    protected abstract int getMaxRestockDelay();

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob)
    {
        return null;
    }

    @Override
    public void die(DamageSource source)
    {
        super.die(source);
        this.setTradingPlayer(null);
    }

    @Override
    @Nullable
    public Entity changeDimension(DimensionTransition transition)
    {
        this.setTradingPlayer(null);
        return super.changeDimension(transition);
    }

    @Override
    protected Vec3 getLeashOffset()
    {
        return new Vec3(0, this.getEyeHeight() - 0.25, 0);
    }

    @Override
    public void setLeashedTo(Entity entity, boolean broadcast)
    {
        // When goblin becomes leashed, remove restriction
        super.setLeashedTo(entity, broadcast);
        this.clearRestriction();
    }

    @Override
    public boolean requiresCustomPersistence()
    {
        if(Config.ENTITIES.preventDespawnIfNamed.get() && this.hasCustomName())
            return true;
        if(this.hasCustomer() || this.despawnDelay != 0)
            return true;
        if(this.isLeashed())
            return true;
        return super.requiresCustomPersistence();
    }

}
