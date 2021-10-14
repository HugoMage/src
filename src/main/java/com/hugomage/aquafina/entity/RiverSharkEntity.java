package com.hugomage.aquafina.entity;

import com.hugomage.aquafina.registry.RegistryHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class RiverSharkEntity extends AbstractGroupFishEntity {
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20).add(Attributes.ATTACK_DAMAGE, 3D).add(Attributes.MOVEMENT_SPEED, 1.0);
    }

    public RiverSharkEntity(EntityType<? extends RiverSharkEntity> type, World world) {
        super(type, world);
    }
    public boolean canBeLeashed(PlayerEntity p_184652_1_) {
        return true;
    }
    protected ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RiverSharkEntity.SwimGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterMobEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, DrownedEntity.class, true));



    }
    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(RegistryHandler.RIVERSHARK_SPAWN_EGG.get());
    }
    @Override
    protected ItemStack getBucketItemStack() {
        return new ItemStack(Items.WATER_BUCKET, 1);
    }
    static class SwimGoal extends RandomSwimmingGoal {
        private final RiverSharkEntity fish;

        public SwimGoal(RiverSharkEntity fish) {
            super(fish, 1.4D, 40);
            this.fish = fish;
        }

        public boolean canUse() {
            return super.canUse();
        }
    }



    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }


}
