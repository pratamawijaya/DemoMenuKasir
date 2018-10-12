package com.pratamawijaya.menukasir.ui.rvitem

import com.pratamawijaya.menukasir.R
import com.pratamawijaya.menukasir.domain.Menu
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_menu.view.menuImg
import kotlinx.android.synthetic.main.item_menu.view.menuName
import kotlinx.android.synthetic.main.item_menu.view.menuPrice

interface MenuListener {
    fun onMenuSelected(menu: Menu)
}

class MenuItem(val menu: Menu,
               val listener: MenuListener?) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.menuName.text = menu.name
        viewHolder.itemView.menuPrice.text = menu.price.toString()
        viewHolder.itemView.setOnClickListener { listener?.onMenuSelected(menu) }

        val img = viewHolder.itemView.menuImg
        Picasso.get().load(menu.image).into(img)
    }

    override fun getLayout(): Int = R.layout.item_menu

}