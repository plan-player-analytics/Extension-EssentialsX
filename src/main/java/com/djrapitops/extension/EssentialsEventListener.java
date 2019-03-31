/*
    Copyright(c) 2019 Risto Lahtela (Rsl1122)

    The MIT License(MIT)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files(the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions :
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
*/
package com.djrapitops.extension;

import com.djrapitops.plan.extension.Caller;
import net.ess3.api.IUser;
import net.ess3.api.events.JailStatusChangeEvent;
import net.ess3.api.events.MuteStatusChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

/**
 * Listener for Essentials events.
 *
 * @author Rsl1122
 */
public class EssentialsEventListener implements Listener {

    private final Caller caller;

    public EssentialsEventListener(Caller caller) {
        this.caller = caller;
    }

    public static void register(Caller caller) {
        Plugin plan = Bukkit.getPluginManager().getPlugin("Plan");
        Bukkit.getPluginManager().registerEvents(new EssentialsEventListener(caller), plan);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJailStatusChange(JailStatusChangeEvent event) {
        IUser affected = event.getAffected();
        UUID playerUUID = affected.getBase().getUniqueId();
        String playerName = affected.getName();

        caller.updatePlayerData(playerUUID, playerName);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMuteStatusChange(MuteStatusChangeEvent event) {
        IUser affected = event.getAffected();
        UUID playerUUID = affected.getBase().getUniqueId();
        String playerName = affected.getName();

        caller.updatePlayerData(playerUUID, playerName);
    }
}