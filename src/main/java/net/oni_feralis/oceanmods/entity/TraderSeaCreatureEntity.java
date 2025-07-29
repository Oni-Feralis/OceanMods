package net.oni_feralis.oceanmods.entity;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.level.Level;

public abstract class TraderSeaCreatureEntity extends AgeableMob implements Merchant
{
    protected TraderSeaCreatureEntity(EntityType<? extends AgeableMob> type, Level level)
    {
        super(type, level);
    }
}
