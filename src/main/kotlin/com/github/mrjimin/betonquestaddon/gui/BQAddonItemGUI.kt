package com.github.mrjimin.betonquestaddon.gui

import com.github.mrjimin.betonquestaddon.api.BQAddonItems
import com.github.mrjimin.betonquestaddon.item.ItemBuilder
import com.github.mrjimin.betonquestaddon.util.toMiniMessage
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.invui.gui.Markers
import xyz.xenondevs.invui.gui.PagedGui
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.invui.window.Window
import java.io.File

object BQAddonItemGUI {

    fun openMainGUI(player: Player) {
        val contentItems = BQAddonItems.firstItemsFromFile().map { (file, builder) ->
            Item.builder()
                .setItemProvider(
                    ItemBuilder(builder.build())
                        .itemName(file.name.toMiniMessage())
                        .build()
                )
                .addClickHandler { openSubGUI(player, file) }
                .build()
        }

        val backItem = createBackItem { player.closeInventory() }

        val gui = createPagedGui(contentItems, backItem)
        openWindow(player, gui)
    }

    fun openSubGUI(player: Player, file: File) {
        val fileItems = BQAddonItems.allItemsFromFile(file)
            .map { giveItem(player, it.build()) }

        val backItem = createBackItem { openMainGUI(player) }

        val gui = createPagedGui(fileItems, backItem)
        openWindow(player, gui)
    }

    private fun createPagedGui(content: List<Item>, backItem: Item): PagedGui<Item> {
        val builder = PagedGui.itemsBuilder()
            .setStructure(
                "x x x x x x x x x",
                "x x x x x x x x x",
                "x x x x x x x x x",
                "x x x x x x x x x",
                "x x x x x x x x x",
                "# # < # b # > # #"
            )
            .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
            .addIngredient('<', Item.simple(ItemBuilder(Material.ARROW).itemName("Previous Page".toMiniMessage()).build()))
            .addIngredient('>', Item.simple(ItemBuilder(Material.ARROW).itemName("Next Page Page".toMiniMessage()).build()))
            .addIngredient('#', Item.simple(ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).hideTooltip(true).build()))
            .addIngredient('b', backItem)
            .setContent(content)

        return builder.build()
    }

    private fun giveItem(player: Player, itemStack: ItemStack): Item {
        return Item.builder()
            .setItemProvider(itemStack)
            .addClickHandler { _, _ ->
                player.inventory.addItem(itemStack)
            }
            .build()
    }

    private fun createBackItem(onClick: () -> Unit): Item {
        return Item.builder()
            .setItemProvider(ItemBuilder(Material.BARRIER).itemName(("Return".toMiniMessage())).build())
            .addClickHandler { onClick() }
            .build()
    }

    private fun openWindow(player: Player, gui: PagedGui<Item>) {
        Window.builder()
            .setTitle("<#888888>BetonQuestAddon Items</#888888>".toMiniMessage())
            .setUpperGui(gui)
            .open(player)
    }
}