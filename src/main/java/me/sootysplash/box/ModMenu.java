// Modified for PVP Hitboxes by fixpot47. Original: Combat Hitboxes by Sootysplash (Apache-2.0).
package me.sootysplash.box;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.Component;
import java.awt.*;

public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            Config config = Config.getInstance();
            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Component.nullToEmpty("Config")).setSavingRunnable(config::save);
            ConfigEntryBuilder cfgent = builder.entryBuilder();
            ConfigCategory behavior = builder.getOrCreateCategory(Component.nullToEmpty("Behavior"));

            behavior.addEntry(cfgent.startBooleanToggle(Component.nullToEmpty("Enabled"), config.enabled).setDefaultValue(true).setTooltip(Component.nullToEmpty("Modify hitbox rendering?")).setSaveConsumer(v -> config.enabled = v).build());
            behavior.addEntry(cfgent.startBooleanToggle(Component.nullToEmpty("Render Eye Height"), config.renderEyeHeight).setDefaultValue(true).setTooltip(Component.nullToEmpty("Render the red line at the entity's eye height")).setSaveConsumer(v -> config.renderEyeHeight = v).build());
            behavior.addEntry(cfgent.startBooleanToggle(Component.nullToEmpty("Render Look Direction"), config.renderLookDir).setDefaultValue(true).setTooltip(Component.nullToEmpty("Render the blue line indicating the direction the entity is facing")).setSaveConsumer(v -> config.renderLookDir = v).build());
            behavior.addEntry(cfgent.startBooleanToggle(Component.nullToEmpty("Target HitBox Color"), config.changeTargetColor).setDefaultValue(true).setTooltip(Component.nullToEmpty("Target hitbox color on targets?")).setSaveConsumer(v -> config.changeTargetColor = v).build());
            behavior.addEntry(cfgent.startBooleanToggle(Component.nullToEmpty("HitBox Hurt"), config.hitBoxHurt).setDefaultValue(false).setTooltip(Component.nullToEmpty("Hitbox hurt color when hurt?")).setSaveConsumer(v -> config.hitBoxHurt = v).build());
            behavior.addEntry(cfgent.startBooleanToggle(Component.nullToEmpty("Line Look Direction"), config.lineLookDir).setDefaultValue(true).setTooltip(Component.nullToEmpty("Instead of the new arrow, draw the entity's look direction as a line")).setSaveConsumer(v -> config.lineLookDir = v).build());
            behavior.addEntry(cfgent.startBooleanToggle(Component.nullToEmpty("Hide Stuck Arrows"), config.hideArrow).setDefaultValue(false).setTooltip(Component.nullToEmpty("Removes bee stingers and arrows visually from other players")).setSaveConsumer(v -> config.hideArrow = v).build());
            behavior.addEntry(cfgent.startBooleanToggle(Component.nullToEmpty("Skip Fireworks"), config.hideFireworks).setDefaultValue(false).setTooltip(Component.nullToEmpty("Skips rendering hitboxes for fireworks")).setSaveConsumer(v -> config.hideFireworks = v).build());
            behavior.addEntry(cfgent.startBooleanToggle(Component.nullToEmpty("Skip Items"), config.hideItems).setDefaultValue(false).setTooltip(Component.nullToEmpty("Skips rendering hitboxes for dropped items")).setSaveConsumer(v -> config.hideItems = v).build());

            ConfigCategory colors = builder.getOrCreateCategory(Component.nullToEmpty("Colors"));
            colors.addEntry(cfgent.startAlphaColorField(Component.nullToEmpty("Base Color"), config.hitBoxColor).setDefaultValue(Color.WHITE.getRGB()).setTooltip(Component.nullToEmpty("The base hitbox's color")).setSaveConsumer(v -> config.hitBoxColor = v).build());
            colors.addEntry(cfgent.startAlphaColorField(Component.nullToEmpty("Eye Color"), config.eyeColor).setDefaultValue(Color.RED.getRGB()).setTooltip(Component.nullToEmpty("The hitbox eye height color")).setSaveConsumer(v -> config.eyeColor = v).build());
            colors.addEntry(cfgent.startAlphaColorField(Component.nullToEmpty("Look Direction Color"), config.lookColor).setDefaultValue(Color.BLUE.getRGB()).setTooltip(Component.nullToEmpty("The hitbox's look direction color")).setSaveConsumer(v -> config.lookColor = v).build());
            colors.addEntry(cfgent.startAlphaColorField(Component.nullToEmpty("Target Color"), config.targetBoxColor).setDefaultValue(Color.RED.getRGB()).setTooltip(Component.nullToEmpty("The hitbox color when the entity is targeted")).setSaveConsumer(v -> config.targetBoxColor = v).build());
            colors.addEntry(cfgent.startAlphaColorField(Component.nullToEmpty("Hurt Color"), config.hurtBoxColor).setDefaultValue(Color.MAGENTA.getRGB()).setTooltip(Component.nullToEmpty("The hitbox color when the entity is on hurt tick")).setSaveConsumer(v -> config.hurtBoxColor = v).build());

            ConfigCategory linesWidths = builder.getOrCreateCategory(Component.nullToEmpty("Line Width"));
            linesWidths.addEntry(cfgent.startFloatField(Component.nullToEmpty("Line Width 1"), config.line1).setMin(0).setMax(25f).setDefaultValue(2.5f).setTooltip(Component.nullToEmpty("The width of the hitbox lines")).setSaveConsumer(v -> config.line1 = v).build());
            linesWidths.addEntry(cfgent.startDoubleField(Component.nullToEmpty("Distance for width 2"), config.distFor2).setMin(0).setMax(256).setDefaultValue(32).setTooltip(Component.nullToEmpty("The distance for Line Width 2 to be used")).setSaveConsumer(v -> config.distFor2 = v).build());
            linesWidths.addEntry(cfgent.startFloatField(Component.nullToEmpty("Line Width 2"), config.line2).setMin(0).setMax(25f).setDefaultValue(2.5f).setTooltip(Component.nullToEmpty("The width of the hitbox lines beyond the set distance")).setSaveConsumer(v -> config.line2 = v).build());

            ConfigCategory outline = builder.getOrCreateCategory(Component.nullToEmpty("Outline"));
            outline.addEntry(cfgent.startBooleanToggle(Component.nullToEmpty("Outline Enabled"), config.outlineEnabled).setDefaultValue(false).setTooltip(Component.nullToEmpty("Enable hitbox outlines")).setSaveConsumer(v -> config.outlineEnabled = v).build());
            outline.addEntry(cfgent.startAlphaColorField(Component.nullToEmpty("Outline Color"), config.outlineColor).setDefaultValue(Color.BLACK.getRGB()).setTooltip(Component.nullToEmpty("The hitbox's outline color")).setSaveConsumer(v -> config.outlineColor = v).build());
            outline.addEntry(cfgent.startFloatField(Component.nullToEmpty("Outline Size Multiplier"), config.outlineMultiplier).setDefaultValue(2).setMin(Math.nextUp(1)).setMax(10F).setTooltip(Component.nullToEmpty("How much the hitbox's line width will be multiplied by for the outline")).setSaveConsumer(v -> config.outlineMultiplier = v).build());

            return builder.build();
        };
    }
}
