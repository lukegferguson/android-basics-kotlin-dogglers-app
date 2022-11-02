/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog
import org.w3c.dom.Text

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // Initialize data with list of dogs
    val dogList: List<Dog> = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // declare and initialize all view components
        val dogPic: ImageView = view!!.findViewById(R.id.dog_pic)
        val dogName: TextView = view!!.findViewById(R.id.dog_name)
        val dogAge: TextView = view!!.findViewById(R.id.dog_age)
        val dogHobby: TextView = view!!.findViewById(R.id.dog_hobby)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        //Inflate and return grid_list_item if viewType is grid, otherwise inflate and return vertical_horizontal_list_item
        var inflatedView: View = if (layout == 3){
            LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_list_item, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_horizontal_list_item, parent, false)
        }

        return DogCardViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = dogList.size //return the size of the data set

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // get data for dog at current position
        val dog = dogList[position]
        //makes for easier references to context?.resources
        val resources = context?.resources
        //set image for view
        holder.dogPic.setImageResource(dog.imageResourceId)
        //set dog name for view
        holder.dogName.text = dog.name
        //pass dog age and hobbies to string resource for formatting
        holder.dogAge.text = resources?.getString(R.string.dog_age, dog.age)
        holder.dogHobby.text = resources?.getString(R.string.dog_hobbies, dog.hobbies)
    }
}
