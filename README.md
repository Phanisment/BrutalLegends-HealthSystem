# Health System
> Concept this plugin is same as Life Steal but have some modified like use totem as hearth and make totem even harder.

## Features
- Health reduce when death
- Disable Totem function
- Disable Evoker drop and make custom drop for Evoker
- Make totem as Hearth
- Limit totem use as hearth
- Force Health(For Support [AuraSkill](https://modrinth.com/plugin/auraskills) but not implemented yet)

## What Mechanic is Change?
### Death Panisment:
- When player death, will reduce max health of player to 1.

### Totem of Undying:
- You can't use totem as normally minecraft because the function is use as heart but you can disbale/enable it on configuration.

### Totem Linimitation Usage:
- You can't use totem more than 3 times, You can change it in configuration but if you want disable it, make the value to 0.

### Evoker Drops:
- Evoker will drop more rare or just disable it on config.

### Force Health:
- Use custom data health, not vanilla attributes beacuse if you use AuraSkill and some skill add more health, make the Death Panisment not work.
- This is not implemented yet but i will added in future.

## Commands

> [!NOTE]
> you wonder why there is only 1 command? because im tired.

### Plugin:
`/hsreload`
> Reload file configuration

### Vanilla:
`/attributes @s minecraft:GENERIC_MAX_HEALTH base set <value>`
> For set health of player. [(Minecraft Wiki)](https://minecraft.wiki/w/Attribute)

`/pardon <player>`
> Unban player.