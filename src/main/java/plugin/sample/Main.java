package plugin.sample;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class Main extends JavaPlugin implements Listener {
    private int count;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
        List<Integer> numberList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numberList.stream()
                .filter(number -> number <= 5)
                .forEach(System.out::println);
    }

    /**
     * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
     *
     * @param e イベント
     */
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) throws IOException {
        // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
        Player player = e.getPlayer();
        World world = player.getWorld();
        //List<Color> colorList = List.of(Color.RED, Color.BLUE, Color.WHITE, Color.BLACK);
        if (count % 2 == 0) {
            // for (Color color : colorList) {

            // 花火オブジェクトをプレイヤーのロケーション地点に対して出現させる
            Firework firework = world.spawn(player.getLocation(), Firework.class);

            // 花火オブジェクトが持つメタ情報を取得
            FireworkMeta fireworkMeta = firework.getFireworkMeta();

            // メタ情報に対して設定を追加したり、値の上書きを行う
            // 今回は青色で星型の花火を打ち上げる
            fireworkMeta.addEffect(
                    FireworkEffect.builder()
                            .withColor(Color.RED)
                            .with(Type.BALL_LARGE)
                            .withFlicker()
                            .build());
            fireworkMeta.setPower(5 % 2);
            // 追加した情報で再設定
            firework.setFireworkMeta(fireworkMeta);
            Path path = Path.of("firework.txt");
            Files.writeString(path, "DAY21課題！！");
            player.sendMessage(Files.readString(path));
        }
        count++;
    }
}
   /* @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent e) {
        Player player = e.getPlayer();
        ItemStack[] itemStacks = player.getInventory().getContents();
        Arrays.stream(itemStacks)
                .filter(item -> !Objects.isNull(item) && item.getMaxStackSize() == 64 && item.getAmount() < 64)
                .forEach(item -> item.setAmount(0));

        player.getInventory().setContents(itemStacks);
    }

    */

