package com.djrapitops.extension;

import com.djrapitops.plan.extension.DataExtension;
import com.djrapitops.plan.extension.NotReadyException;
import com.djrapitops.plan.extension.annotation.DoubleProvider;
import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@PluginInfo(name = "EssentialsEco", iconName = "coins", iconFamily = Family.SOLID, color = Color.DEEP_ORANGE)
public class EssentialsEconomyExtension implements DataExtension {

    public static AtomicBoolean enabled = new AtomicBoolean();

    private Essentials essentials;

    EssentialsEconomyExtension(boolean b) {
        /* For test construction */
    }

    EssentialsEconomyExtension() {
        essentials = JavaPlugin.getPlugin(Essentials.class);
        enabled.set(!essentials.getSettings().isEcoDisabled());
    }

    @DoubleProvider(
            text = "Balance", iconName = "coins", priority = 60, iconColor = Color.GREEN
    )
    public double balance(UUID playerUUID) {
        if (essentials.getSettings().isEcoDisabled()) {
            throw new NotReadyException();
        }

        return getUser(playerUUID).getMoney().doubleValue();
    }

    private User getUser(UUID playerUUID) {
        User user = essentials.getUser(playerUUID);
        if (user == null) throw new NotReadyException();
        return user;
    }

}
