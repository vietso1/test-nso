package com.nsoz.event;

import com.nsoz.bot.Bot;
import com.nsoz.bot.Principal;
import com.nsoz.bot.move.PrincipalMove;
import com.nsoz.constants.ItemOptionName;
import com.nsoz.constants.CMDInputDialog;
import com.nsoz.constants.CMDMenu;
import com.nsoz.constants.ConstTime;
import com.nsoz.constants.ItemName;
import com.nsoz.constants.MapName;
import com.nsoz.constants.MobName;
import com.nsoz.constants.NpcName;
import com.nsoz.event.eventpoint.EventPoint;
import com.nsoz.item.Item;
import com.nsoz.item.ItemFactory;
import com.nsoz.lib.RandomCollection;
import com.nsoz.map.Map;
import com.nsoz.map.MapManager;
import com.nsoz.map.zones.Zone;
import com.nsoz.mob.Mob;
import com.nsoz.model.Char;
import com.nsoz.model.InputDialog;
import com.nsoz.model.Menu;
import com.nsoz.npc.Npc;
import com.nsoz.npc.NpcFactory;
import com.nsoz.option.ItemOption;
import com.nsoz.server.GlobalService;
import com.nsoz.store.ItemStore;
import com.nsoz.store.StoreManager;
import com.nsoz.util.NinjaUtils;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author kitakeyos - Hoàng Hữu Dũng
 */
public class SummerEvent extends Event {
    

    public static final String TOP_BOSS = "Tu_Loi_Thien_Long";
    public static final String TOP_DIEU_VAI = "Dieu_vai";
    private static final int MAKE_GIAY_CAKE = 0;
    private static final int MAKE_VAI_CAKE = 1;
    public RandomCollection<Integer> vipItems = new RandomCollection<>();
    private ZonedDateTime start, end;

    public SummerEvent() {
        setId(Event.SUMMER);
        endTime.set(2023, 7, 15, 26, 59, 59);
        itemsThrownFromMonsters.add(1, ItemName.DAY);
        itemsThrownFromMonsters.add(1, ItemName.GIAY2);
        itemsThrownFromMonsters.add(1, ItemName.TRE);
        itemsThrownFromMonsters.add(1, ItemName.VAI);
        itemsThrownFromMonsters.add(1, ItemName.KEM_SUA);
        itemsThrownFromMonsters.add(1, ItemName.KEM_DAU);
        itemsThrownFromMonsters.add(1, ItemName.KEM_OC_QUE);
        itemsThrownFromMonsters.add(1, ItemName.KEM_CHOCOLATE);

        keyEventPoint.add(EventPoint.DIEM_TIEU_XAI);
        keyEventPoint.add(TOP_BOSS);
        keyEventPoint.add(TOP_DIEU_VAI);

        itemsRecFromGoldItem.add(1, ItemName.HOA_KY_LAN);
        itemsRecFromGoldItem.add(1, ItemName.SHIRAIJI);
        itemsRecFromGoldItem.add(1, ItemName.HAJIRO);
        itemsRecFromGoldItem.add(1, ItemName.TUI_VAI_CAP_5);
        itemsRecFromGoldItem.add(2, ItemName.BACH_HO);
        itemsRecFromGoldItem.add(2, ItemName.LAN_SU_VU);
        itemsRecFromGoldItem.add(1, ItemName.PET_UNG_LONG);
        itemsRecFromGoldItem.add(2, ItemName.GAY_TRAI_TIM);
        itemsRecFromGoldItem.add(2, ItemName.GAY_MAT_TRANG);
        itemsRecFromGoldItem.add(15, ItemName.DA_DANH_VONG_CAP_1);
        itemsRecFromGoldItem.add(12, ItemName.DA_DANH_VONG_CAP_2);
        itemsRecFromGoldItem.add(9, ItemName.DA_DANH_VONG_CAP_3);
        itemsRecFromGoldItem.add(7, ItemName.DA_DANH_VONG_CAP_4);
        itemsRecFromGoldItem.add(5, ItemName.DA_DANH_VONG_CAP_5);
        itemsRecFromGoldItem.add(15, ItemName.VIEN_LINH_HON_CAP_1);
        itemsRecFromGoldItem.add(12, ItemName.VIEN_LINH_HON_CAP_2);
        itemsRecFromGoldItem.add(9, ItemName.VIEN_LINH_HON_CAP_3);
        itemsRecFromGoldItem.add(7, ItemName.VIEN_LINH_HON_CAP_4);
        itemsRecFromGoldItem.add(5, ItemName.VIEN_LINH_HON_CAP_5);
        itemsRecFromGoldItem.add(5, ItemName.CAT_NGU_SAC);

        itemsRecFromGold2Item.add(1, ItemName.HOA_KY_LAN);
        itemsRecFromGold2Item.add(1, ItemName.SHIRAIJI);
        itemsRecFromGold2Item.add(1, ItemName.HAJIRO);
        itemsRecFromGold2Item.add(2, ItemName.BACH_HO);
        itemsRecFromGold2Item.add(2, ItemName.LAN_SU_VU);
        itemsRecFromGold2Item.add(1, ItemName.PET_UNG_LONG);
        itemsRecFromGold2Item.add(2, ItemName.GAY_TRAI_TIM);
        itemsRecFromGold2Item.add(2, ItemName.GAY_MAT_TRANG);

        vipItems.add(1, ItemName.HOA_KY_LAN);
        vipItems.add(2, ItemName.BACH_HO);
        vipItems.add(2, ItemName.PET_UNG_LONG);
        vipItems.add(2, ItemName.SHIRAIJI);
        vipItems.add(2, ItemName.HAJIRO);
        vipItems.add(4, ItemName.GAY_TRAI_TIM);
        vipItems.add(3, ItemName.GAY_MAT_TRANG);
         vipItems.add(3, ItemName.TUI_VAI_CAP_5);
         
    }
    
    

   

  @Override
    public void initStore() {
        
         StoreManager.getInstance().addItem((byte) StoreManager.TYPE_MISCELLANEOUS, ItemStore.builder()
                .id(433)
                .itemID(ItemName.MAU_VE_CAO_CAP)
                .gold(20)
                .expire(ConstTime.FOREVER)
                .build());
        StoreManager.getInstance().addItem((byte) StoreManager.TYPE_MISCELLANEOUS, ItemStore.builder()
                .id(432)
                .itemID(ItemName.MAU_VE_THO_SO)
                .coin(50000)
                .expire(ConstTime.FOREVER)
                .build());
        StoreManager.getInstance().addItem((byte) StoreManager.TYPE_MISCELLANEOUS, ItemStore.builder()
                .id(548)
                .itemID(ItemName.CAN_CAU_VANG)
                .gold(50)
                .expire(ConstTime.FOREVER)
                .build());
       
        
    }

    @Override
    public void action(Char p, int type, int amount) {
        if (isEnded()) {
            p.serverMessage("Sự kiện đã kết thúc");
            return;
        }
        switch (type) {
            case MAKE_GIAY_CAKE:
                makeGiayCake(p, amount);
                break;
            case MAKE_VAI_CAKE:
                makeVaiCake(p, amount);
                break;

        }
    }

    private void makeVaiCake(Char p, int amount) {
        int[][] itemRequires = new int[][] { { ItemName.TRE, 5 },{ItemName.VAI, 5},{ItemName.DAY, 5},{ItemName.MAU_VE_CAO_CAP, 1},};
        int itemIdReceive = ItemName.DIEU_VAI;
        boolean isDone = makeEventItem(p, amount, itemRequires, 0, 0, 0, itemIdReceive);
         p.getEventPoint().addPoint(TOP_DIEU_VAI, 1);
        if (isDone) {
            p.getEventPoint().addPoint(SummerEvent.TOP_DIEU_VAI, amount);
           
        }
    }

    private void makeGiayCake(Char p, int amount) {
        int[][] itemRequires = new int[][] { { ItemName.TRE, 5 },{ItemName.GIAY2, 5},{ItemName.DAY, 5},{ItemName.MAU_VE_THO_SO, 1},};
        int itemIdReceive = ItemName.DIEU_GIAY;
        makeEventItem(p, amount, itemRequires, 0, 0, 0, itemIdReceive);
    }

    

    private void exchangeAkatsuki(Char p) {
        int indexTetCake = p.getIndexItemByIdInBag(ItemName.DIEU_VAI);
        if (indexTetCake == -1 || p.bag[indexTetCake] == null || p.bag[indexTetCake].getQuantity() < 20) {
            p.getService().npcChat(NpcName.TIEN_NU, "Ngươi cần có đủ 20 diều giấy");
            return;
        }
        if (p.getSlotNull() == 0) {
            p.getService().npcChat(NpcName.TIEN_NU, p.language.getString("BAG_FULL"));
            return;
        }
        if (p.user.gold < 500) {
            p.getService().npcChat(NpcName.TIEN_NU, "Ngươi phải có đủ 500 lượng.");
            return;
        }

        int dressId = p.gender == 1 ? ItemName.AO_AKATSUKI_NAM : ItemName.AO_AKATSUKI_NU;

        p.removeItem(indexTetCake, 20, true);
        p.addGold(-500);
        Item item = ItemFactory.getInstance().newItem(dressId);
        item.isLock = false;
        item.expire = System.currentTimeMillis() + 1296000000L;

        item.randomOptionTigerMask();

        p.addItemToBag(item);
    }

   

    @Override
    public void menu(Char p) {
        p.menus.clear();
        p.menus.add(new Menu(CMDMenu.EXECUTE, "Làm Diều", () -> {
            p.menus.add(new Menu(CMDMenu.EXECUTE, "Diều Giấy", () -> {
                p.setInput(new InputDialog(CMDInputDialog.EXECUTE, "Diều Giấy", () -> {
                    InputDialog input = p.getInput();
                    try {
                        int number = input.intValue();
                        action(p, MAKE_GIAY_CAKE, number);
                    } catch (NumberFormatException e) {
                        if (!input.isEmpty()) {
                            p.inputInvalid();
                        }
                    }
                }));
                p.getService().showInputDialog();
            }));
            p.menus.add(new Menu(CMDMenu.EXECUTE, "Diều Vải", () -> {
                p.setInput(new InputDialog(CMDInputDialog.EXECUTE, "Diều Vải", () -> {
                    InputDialog input = p.getInput();
                    try {
                        int number = input.intValue();
                        action(p, MAKE_VAI_CAKE, number);
                    } catch (NumberFormatException e) {
                        if (!input.isEmpty()) {
                            p.inputInvalid();
                        }
                    }
                }));
                p.getService().showInputDialog();
            }));
            p.getService().openUIMenu();
        }));
        

        

         p.menus.add(new Menu(CMDMenu.EXECUTE, "Đua TOP", () -> {
            p.menus.clear();
            p.menus.add(new Menu(CMDMenu.EXECUTE, "Diều Vải", () -> {
                viewTop(p, TOP_DIEU_VAI, "Diều vải", "%d. %s đã mở %s Diều vải");
            }));
           p.menus.add(new Menu(CMDMenu.EXECUTE, "Phần thưởng", () -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Top 1:").append("\n");
                    sb.append("- Akatsuki vĩnh viễn\n");
                    sb.append("- Mặt nạ hổ vĩnh viễn\n");
                    sb.append("- Bạch hổ vv ( mcs)\n");
                    sb.append("- 2 mặt nạ trang bị 2 ( vĩnh viễn )\n");
                    sb.append("- 10 Pháp khí\n\n");
                    sb.append("Top 2:").append("\n");
                    sb.append("- Shiraiji/Hajiro vĩnh viễn\n");
                    sb.append("- Gậy thời trang vĩnh viễn\n");
                    sb.append("- Bạch hổ vv ( randum chỉ số )\n");
                    sb.append("- 1 mặt nạ trang bị 2 ( vĩnh viễn )\n");
                    sb.append("- 5 pháp khí\n\n");
                    sb.append("Top 3:").append("\n");
                    sb.append("- Shiraiji/Hajiro 3 tháng\n");
                    sb.append("- Gậy thời trang 3 tháng\n");
                    sb.append("- Bạch hổ 3 tháng ( randum chỉ số )\n");
                    sb.append("- 1 mặt nạ trang bị 2 ( 3 tháng )\n");
                    sb.append("- 3 pháp khí\n\n");
                    sb.append("Top 4 - 10:").append("\n");
                    sb.append("- Shiraiji/Hajiro 1 tháng\n");
                    sb.append("- Gậy mặt trăng 1 tháng\n");
                    sb.append("- Bạch hổ 1 tháng ( randum chỉ số )\n");
                    sb.append("- 1 pháp khí\n");
                    p.getService().showAlert("Phần thưởng", sb.toString());
                }));
            if (isEnded()) {
                int ranking = getRanking(p, TOP_DIEU_VAI);
                if (ranking <= 10 && p.getEventPoint().getRewarded(TOP_DIEU_VAI) == 0) {
                    p.menus.add(new Menu(CMDMenu.EXECUTE, String.format("Nhận Thưởng TOP %d", ranking), () -> {
                        receiveReward(p, TOP_DIEU_VAI);
                    }));
                }
            }
            p.getService().openUIMenu();
        }));

        

    }

    public void makePreciousTree(Char p, int type) {
        int point = type == 1 ? 5000 : 20000;
        if (p.getEventPoint().getPoint(EventPoint.DIEM_TIEU_XAI) < point) {
            p.getService().npcChat(NpcName.TIEN_NU,
                    "Ngươi cần tối thiểu " + NinjaUtils.getCurrency(point)
                            + " điểm sự kiện mới có thể đổi được vật này.");
            return;
        }

        if (p.getSlotNull() == 0) {
            p.getService().npcChat(NpcName.TIEN_NU, p.language.getString("BAG_FULL"));
            return;
        }

        Item item = ItemFactory.getInstance().newItem(type == 1 ? ItemName.LAM_SON_DA : ItemName.TRUC_BACH_THIEN_LU);
        p.addItemToBag(item);
        p.getEventPoint().subPoint(EventPoint.DIEM_TIEU_XAI, point);
    }

    @Override
    public void initMap(Zone zone) {
        Map map = zone.map;
        int mapID = map.id;
        switch (mapID) {

            case MapName.RUNG_DAO_SAKURA:
                if (zone.id == 15) {
                 Mob  monster = new Mob(zone.getMonsters().size(), (short) MobName.TU_LOI_DIEU_THIEN_LONG, 1000000000, (byte) 100,
                            (short) 1928, (short) 240, false, true, zone);
                    zone.addMob(monster);
                }
                break;
        }
    }

    public void receiveReward(Char p, String key) {
        int ranking = getRanking(p, key);
        if (ranking > 10) {
            p.getService().serverDialog("Bạn không đủ điều kiện nhận phần thưởng");
            return;
        }
        if (p.getEventPoint().getRewarded(key) == 1) {
            p.getService().serverDialog("Bạn đã nhận phần thưởng rồi");
            return;
        }
        if (p.getSlotNull() < 10) {
            p.getService().serverDialog("Bạn cần để hành trang trống tối thiểu 10 ô");
            return;
        }

        if (key == TOP_BOSS) {
            topDecorationGiftBox(ranking, p);
        } else if (key == TOP_DIEU_VAI) {
            topMakeChungCake(ranking, p);
        }
        p.getEventPoint().setRewarded(key, 1);
    }

    public void topDecorationGiftBox(int ranking, Char p) {
        Item mount = ItemFactory.getInstance().newItem(ItemName.HOA_KY_LAN);
        int dressId = p.gender == 1 ? ItemName.AO_NGU_THAN : ItemName.AO_TAN_THOI;
        Item aoDai = ItemFactory.getInstance().newItem(dressId);
        Item tree = ItemFactory.getInstance().newItem(ItemName.TRUC_BACH_THIEN_LU);
        if (ranking == 1) {
            mount.options.add(new ItemOption(ItemOptionName.NE_DON_ADD_POINT_TYPE_1, 200));
            mount.options.add(new ItemOption(ItemOptionName.CHINH_XAC_ADD_POINT_TYPE_1, 100));
            mount.options.add(new ItemOption(ItemOptionName.TAN_CONG_KHI_DANH_CHI_MANG_POINT_PERCENT_TYPE_1, 100));
            mount.options.add(new ItemOption(ItemOptionName.CHI_MANG_ADD_POINT_TYPE_1, 100));
            mount.options.add(new ItemOption(58, 10));
            mount.options.add(new ItemOption(128, 10));
            mount.options.add(new ItemOption(127, 10));
            mount.options.add(new ItemOption(130, 10));
            mount.options.add(new ItemOption(131, 10));

            aoDai.options.add(new ItemOption(125, 3000));
            aoDai.options.add(new ItemOption(117, 3000));
            aoDai.options.add(new ItemOption(94, 10));
            aoDai.options.add(new ItemOption(136, 30));
            aoDai.options.add(new ItemOption(127, 10));
            aoDai.options.add(new ItemOption(130, 10));
            aoDai.options.add(new ItemOption(131, 10));

            tree.setQuantity(10);
            p.addItemToBag(tree);
            for (int i = 0; i < 3; i++) {
                Item mysteryChest = ItemFactory.getInstance().newItem(ItemName.RUONG_HUYEN_BI);
                p.addItemToBag(mysteryChest);
            }
        } else if (ranking == 2) {
            tree.setQuantity(5);
            p.addItemToBag(tree);
            Item mysteryChest = ItemFactory.getInstance().newItem(ItemName.RUONG_HUYEN_BI);
            p.addItemToBag(mysteryChest);
        } else if (ranking >= 3 && ranking <= 5) {
            mount.expire = System.currentTimeMillis() + ConstTime.DAY * 90L;
            aoDai.expire = System.currentTimeMillis() + ConstTime.DAY * 90L;
            tree.setQuantity(3);
            p.addItemToBag(tree);
            for (int i = 0; i < 2; i++) {
                Item blueChest = ItemFactory.getInstance().newItem(ItemName.RUONG_BACH_NGAN);
                p.addItemToBag(blueChest);
            }
        } else {
            mount.expire = System.currentTimeMillis() + ConstTime.DAY * 30L;
            aoDai.expire = System.currentTimeMillis() + ConstTime.DAY * 30L;
            Item blueChest = ItemFactory.getInstance().newItem(ItemName.RUONG_BACH_NGAN);
            p.addItemToBag(blueChest);
        }

        p.addItemToBag(mount);
        p.addItemToBag(aoDai);
    }

    public void topMakeChungCake(int ranking, Char p) {
        Item pet = ItemFactory.getInstance().newItem(ItemName.PET_UNG_LONG);
        int tickId = p.gender == 1 ? ItemName.GAY_MAT_TRANG : ItemName.GAY_TRAI_TIM;
        Item fashionStick = ItemFactory.getInstance().newItem(tickId);
        Item tree = ItemFactory.getInstance().newItem(ItemName.TRUC_BACH_THIEN_LU);
        if (ranking == 1) {
            pet.options.add(new ItemOption(ItemOptionName.HP_TOI_DA_ADD_POINT_TYPE_1, 3000));
            pet.options.add(new ItemOption(ItemOptionName.MP_TOI_DA_ADD_POINT_TYPE_1, 3000));
            pet.options.add(new ItemOption(ItemOptionName.CHI_MANG_POINT_TYPE_1, 100)); // chi mang
            pet.options.add(new ItemOption(ItemOptionName.TAN_CONG_ADD_POINT_PERCENT_TYPE_8, 10));
            pet.options.add(new ItemOption(ItemOptionName.MOI_5_GIAY_PHUC_HOI_MP_POINT_TYPE_1, 200));
            pet.options.add(new ItemOption(ItemOptionName.MOI_5_GIAY_PHUC_HOI_HP_POINT_TYPE_1, 200));
            pet.options.add(new ItemOption(ItemOptionName.KHONG_NHAN_EXP_TYPE_0, 1));

            tree.setQuantity(10);
            p.addItemToBag(tree);
            for (int i = 0; i < 3; i++) {
                Item mysteryChest = ItemFactory.getInstance().newItem(ItemName.RUONG_HUYEN_BI);
                p.addItemToBag(mysteryChest);
            }
        } else if (ranking == 2) {
            tree.setQuantity(5);
            p.addItemToBag(tree);
            Item mysteryChest = ItemFactory.getInstance().newItem(ItemName.RUONG_HUYEN_BI);
            p.addItemToBag(mysteryChest);
        } else if (ranking >= 3 && ranking <= 5) {
            pet.expire = System.currentTimeMillis() + ConstTime.DAY * 90L;
            fashionStick.expire = System.currentTimeMillis() + ConstTime.DAY * 90L;
            tree.setQuantity(3);
            p.addItemToBag(tree);
            for (int i = 0; i < 2; i++) {
                Item blueChest = ItemFactory.getInstance().newItem(ItemName.RUONG_BACH_NGAN);
                p.addItemToBag(blueChest);
            }
        } else {
            pet.expire = System.currentTimeMillis() + ConstTime.DAY * 30L;
            fashionStick.expire = System.currentTimeMillis() + ConstTime.DAY * 30L;
            Item blueChest = ItemFactory.getInstance().newItem(ItemName.RUONG_BACH_NGAN);
            p.addItemToBag(blueChest);
        }

        p.addItemToBag(pet);
        p.addItemToBag(fashionStick);
    }

    @Override
    public void useItem(Char p, Item item) {
        switch (item.id) {
            case ItemName.DIEU_VAI:
                if (p.getSlotNull() == 0) {
                    p.warningBagFull();
                    return;
                }
                useEventItem(p, item.id, itemsRecFromGoldItem);
                p.getEventPoint().addPoint(TOP_DIEU_VAI, 1);
                break;
            case ItemName.DIEU_GIAY:
                if (p.getSlotNull() == 0) {
                    p.warningBagFull();
                    return;
                }
                useEventItem(p, item.id, itemsRecFromCoinItem);
                break;
  
        }
    }

}
