package org.runestar.client.plugins.dev.widgetexplorer

import org.runestar.client.api.ICON
import org.runestar.client.game.api.Widget
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel

class ExplorerFrame : JFrame("Widget Explorer") {

    private val tree: JTree

    private val treeModel: DefaultTreeModel

    private val textArea = JTextArea()

    private val root = DefaultMutableTreeNode("root")

    private val orderComboBox = JComboBox<WidgetOrder>(arrayOf(WidgetOrder.Id, WidgetOrder.Hierarchy))

    @Volatile
    var selectedWidget: Widget? = null

    init {
        iconImage = ICON
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        treeModel = DefaultTreeModel(root)
        tree = JTree(treeModel)
        tree.isRootVisible = false
        tree.showsRootHandles = true
        textArea.isEditable = false
        add(
                JSplitPane(
                    JSplitPane.HORIZONTAL_SPLIT,
                    JPanel(BorderLayout()).apply {
                        add(
                                JScrollPane(tree).apply {
                                    preferredSize = Dimension(200, 400)
                                },
                                BorderLayout.CENTER
                        )
                        add(
                                Box.createHorizontalBox().apply {
                                    add(JLabel("Order: "))
                                    add(orderComboBox)
                                    add(Box.createGlue())
                                    add(JButton("Reload").apply {
                                        addActionListener { reload() }
                                    })
                                },
                                BorderLayout.SOUTH
                        )
                    },
                    JScrollPane(textArea).apply {
                        preferredSize = Dimension(200, 400)
                    }
                )
        )
        reload()
        pack()
        tree.selectionModel.addTreeSelectionListener {
            val node = tree.lastSelectedPathComponent as? DefaultMutableTreeNode ?: return@addTreeSelectionListener
            val ww = node.userObject as? WidgetWrapper ?: return@addTreeSelectionListener
            val w = ww.widget
            selectedWidget = w
            fillTextArea(textArea, w)
        }
        isVisible = true
    }

    private fun reload() {
        val selected = orderComboBox.selectedItem as? WidgetOrder ?: return
        selected.reloadTree(treeModel)
    }

    private fun fillTextArea(ta: JTextArea, w: Widget) {
        val x = w.accessor
        val s =
                """
$w

hierarchy:
${w.hierarchyString()}

x: ${x.x}
y: ${x.y}
width: ${x.width}
height: ${x.height}
paddingX: ${x.paddingX}
paddingY: ${x.paddingY}
scrollX: ${x.scrollX}
scrollY: ${x.scrollY}
scrollWidth: ${x.scrollWidth}
scrollHeight: ${x.scrollHeight}

rootIndex: ${x.rootIndex}
cycle: ${x.cycle}
isHidden: ${x.isHidden}

text: ${x.text}
color: ${x.color}
dataText: ${x.dataText}
actions: ${x.actions?.contentToString()}
buttonText: ${x.buttonText}
spellActionName: ${x.spellActionName}
spellName: ${x.spellName}

fontId: ${x.fontId}

type: ${x.type}
contentType: ${x.contentType}

itemId: ${x.itemId}
itemQuantity: ${x.itemQuantity}

itemIds: ${x.itemIds?.contentToString()}
itemQuantities: ${x.itemQuantities?.contentToString()}

noClickThrough: ${x.noClickThrough}
spriteId: ${x.spriteId}
spriteId2: ${x.spriteId2}
"""
        ta.text = s
        ta.moveCaretPosition(0)
    }

    private fun Widget.hierarchyString(): String {
        val sb = StringBuilder()
        var w: Widget? = this
        while (w != null) {
            sb.append(w.idString())
            sb.append(" >\n")
            w = w.parent
        }
        sb.deleteCharAt(sb.length - 1)
        return sb.toString()
    }
}