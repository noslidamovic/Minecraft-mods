package net.minecraft.src;
import java.util.*;
import java.io.*;
import net.minecraft.client.Minecraft;

public class mod_OldDaysEyecandy extends mod_OldDays{
    public void load(){
        addProperty(this, 4, 1, "Old walking",         true,  "OldWalking");
        addProperty(this, 4, 2, "Bobbing",             false, "Bobbing");
        addProperty(this, 4, 3, "Old endermen",        true,  "OldEndermen");
        addProperty(this, 4, 4, "Endermen open mouth", true,  "EndermenOpenMouth");
        addProperty(this, 4, 5, "Item sway",           true,  "ItemSway");
        addProperty(this, 4, 6, "2D items",            false, "Items2D");
        addProperty(this, 4, 7, "Old chests",          true,  "OldChest");
        addProperty(this, 4, 8, "Show mob IDs in F3",  true,  "MobLabels");
        addProperty(this, 4, 9, "Mob armor",           false, "MobArmor");
        addProperty(this, 4, 10,"Old main menu",       true,  "OldMainMenu");
        loadModuleProperties(4);
        replaceBlocks();
    }

    public void callback (int i){
        switch (i){
            case 1: ModelBiped.oldwalking =             OldWalking;        break;
            case 2: RenderLiving.bobbing =              Bobbing;           break;
            case 3: EntityEnderman.smoke =              OldEndermen;
                    RenderEnderman2.greeneyes =         OldEndermen;       break;
            case 4: ModelEnderman.openmouth =           EndermenOpenMouth; break;
            case 5: ItemRenderer.sway =                 ItemSway;          break;
            case 6: ItemRenderer.items2d =              Items2D;           break;
            case 7: BlockChestOld.normalblock =         OldChest;
                    TileEntityChestRenderer.hidemodel = OldChest;
                    RenderMinecart2.shiftChest =        OldChest;
                    ModLoader.getMinecraftInstance().renderGlobal.loadRenderers(); break;
            case 8: RenderLiving.labels =               MobLabels;         break;
            case 9: RenderZombie.mobArmor =             MobArmor;
                    RenderSkeleton.mobArmor =           MobArmor;          break;
            case 10:GuiMainMenu.panorama =             !OldMainMenu;
                    GuiMainMenu.oldlogo =               OldMainMenu;       break;
        }
    }

    public void addRenderer(Map map){
        map.put(net.minecraft.src.EntityEnderman.class, new RenderEnderman2());
        map.put(net.minecraft.src.EntityMinecart.class, new RenderMinecart2());
        map.put(net.minecraft.src.EntityZombie.class, new RenderZombie(new ModelZombie()));
        map.put(net.minecraft.src.EntitySkeleton.class, new RenderSkeleton(new ModelSkeleton()));
    }

    public static boolean ItemSway = true;
    public static boolean Items2D;
    public static boolean Bobbing;
    public static boolean OldWalking = true;
    public static boolean OldEndermen = true;
    public static boolean EndermenOpenMouth = true;
    public static boolean OldChest = true;
    public static boolean MobLabels = true;
    public static boolean MobArmor;
    public static boolean OldMainMenu = true;

    private void replaceBlocks(){
        try{
            Block.blocksList[Block.chest.blockID] = null;
            BlockChestOld customchest = (BlockChestOld)(new BlockChestOld(54)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setBlockName("chest").setRequiresSelfNotify();
            Block.blocksList[Block.chest.blockID] = customchest;
        }catch (Exception exception){
            System.out.println(exception);
        }
    }
}