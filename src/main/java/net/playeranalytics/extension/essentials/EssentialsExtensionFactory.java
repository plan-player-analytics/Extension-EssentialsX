/*
    Copyright(c) 2019 AuroraLS3

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
package net.playeranalytics.extension.essentials;

import com.djrapitops.plan.extension.Caller;
import com.djrapitops.plan.extension.DataExtension;
import org.bukkit.Bukkit;

import java.util.Optional;

/**
 * Factory for DataExtension.
 *
 * @author AuroraLS3
 */
public class EssentialsExtensionFactory {

    private boolean isAvailable() {
        try {
            Class.forName("com.earth2me.essentials.Essentials");
            return Bukkit.getPluginManager().isPluginEnabled("Essentials");
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public Optional<DataExtension> createExtension() {
        if (isAvailable()) {
            return Optional.of(new EssentialsExtension());
        }
        return Optional.empty();
    }

    public Optional<DataExtension> createEcoExtension() {
        if (isAvailable()) {
            EssentialsEconomyExtension extension = new EssentialsEconomyExtension();
            if (EssentialsEconomyExtension.enabled.get()) {
                return Optional.of(extension);
            }
        }
        return Optional.empty();
    }

    public void registerUpdateListeners(Caller caller) {
        EssentialsEventListener.register(caller);
    }

    public void registerEconomyUpdateListeners(Caller caller) {
        EssentialsEconomyEventListener.register(caller);
    }
}