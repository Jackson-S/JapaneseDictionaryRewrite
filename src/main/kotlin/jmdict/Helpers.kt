package jmdict

import org.w3c.dom.Node
import org.w3c.dom.NodeList

inline fun <R> NodeList.map(action: (Node) -> R) = (0 until this.length).map { action(this.item(it)) }

fun NodeList.first() = if (this.length != 0) this.item(0) else null
