package aurocosh.divinefavor.common.potions.common

import aurocosh.divinefavor.common.potions.base.potion.ModPotion
import aurocosh.divinefavor.common.potions.base.potion.ModPotionCharge
import aurocosh.divinefavor.common.potions.base.potion.ModPotionToggle
import aurocosh.divinefavor.common.potions.base.potion.ModPotionToggleLimited
import aurocosh.divinefavor.common.potions.potions.*
import net.minecraft.block.material.Material

object ModPotions {
    lateinit var armor_of_pacifist: ModPotionToggle
    lateinit var arrow_deflection: ModPotion
    lateinit var consuming_fury: ModPotion
    lateinit var crushing_palm: ModPotionToggleLimited
    lateinit var crystalline_road: ModPotion
    lateinit var empower_axe: ModPotion
    lateinit var empower_pickaxe: ModPotion
    lateinit var escape_plan: ModPotion
    lateinit var extreme_buoyancy: ModPotionToggle
    lateinit var fall_negation: ModPotionCharge
    lateinit var fins: ModPotion
    lateinit var focused_fury: ModPotion
    lateinit var gills: ModPotionToggle
    lateinit var ground_flow: ModPotionToggle
    lateinit var grudge: ModPotionToggle
    lateinit var hovering: ModPotionToggle
    lateinit var instant_dive: ModPotionToggle
    lateinit var miners_focus: ModPotion
    lateinit var mist_blade: ModPotionToggle
    lateinit var molten_skin: ModPotionToggle
    lateinit var night_eye: ModPotionToggle
    lateinit var obsidian_road: ModPotion
    lateinit var prismatic_eyes: ModPotionToggle
    lateinit var rotten_might: ModPotionToggle
    lateinit var spider_might: ModPotionToggle
    lateinit var starvation: ModPotion
    lateinit var stone_fever: ModPotion
    lateinit var toadic_jump: ModPotionToggleLimited
    lateinit var wild_charge: ModPotion
    lateinit var wild_sprint: ModPotion
    lateinit var wooden_punch: ModPotionToggleLimited

    fun preInit() {
        armor_of_pacifist = PotionArmorOfPacifist()
        arrow_deflection = PotionArrowDeflection()
        consuming_fury = PotionConsumingFury()
        crushing_palm = PotionCrushingPalm()
        crystalline_road = PotionLiquidWalking("crystalline_road") { world, pos -> world.getBlockState(pos).material == Material.WATER }
        empower_axe = PotionEmpowerAxe()
        empower_pickaxe = PotionEmpowerPickaxe()
        escape_plan = PotionEscapePlan()
        extreme_buoyancy = PotionExtremeBuoyancy()
        fall_negation = PotionFallNegation()
        fins = PotionFins()
        focused_fury = PotionFocusedFury()
        gills = PotionGills()
        ground_flow = PotionGroundFlow()
        grudge = PotionGrudge()
        hovering = PotionHovering()
        instant_dive = PotionInstantDive()
        miners_focus = PotionMinersFocus()
        mist_blade = PotionMistBlade()
        molten_skin = PotionMoltenSkin()
        night_eye = PotionNightEye()
        obsidian_road = PotionLiquidWalking("obsidian_road") { world, pos -> world.getBlockState(pos).material == Material.LAVA }
        prismatic_eyes = PotionPrismaticEyes()
        rotten_might = PotionRottenMight()
        spider_might = PotionSpiderMight()
        starvation = PotionStarvation()
        stone_fever = PotionStoneFever()
        toadic_jump = PotionToadicJump()
        wild_charge = PotionWildCharge()
        wild_sprint = PotionWildSprint()
        wooden_punch = PotionWoodenPunch()
    }
}
