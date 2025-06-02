package com.example.material_components_demo.ui.home.color

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.material_components_demo.R
import com.google.android.material.R as MaterialR

class ColorFragment : Fragment() {

    private lateinit var colorsLayoutSurfaces: LinearLayout
    private lateinit var colorsLayoutContent: LinearLayout
    private lateinit var colorsLayoutUtility: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cat_colors_fragment, container, false)

        // Initialize the LinearLayouts from the layout file
        colorsLayoutSurfaces = view.findViewById(R.id.cat_colors_surfaces)
        colorsLayoutContent = view.findViewById(R.id.cat_colors_content)
        colorsLayoutUtility = view.findViewById(R.id.cat_colors_utility)

        // Populate the layouts with color roles
        for (colorRow in getColorRolesSurfaces()) {
            colorRow.addTo(inflater, colorsLayoutSurfaces)
        }

        for (colorRow in getColorRolesContent()) {
            colorRow.addTo(inflater, colorsLayoutContent)
        }

        for (colorRow in getColorRolesUtility()) {
            colorRow.addTo(inflater, colorsLayoutUtility)
        }

        return view
    }

    private fun getColorRolesSurfaces(): List<ColorRow> {
        return listOf(
            ColorRow(
                ColorRoleItem(R.string.cat_color_role_background, android.R.attr.colorBackground),
                ColorRoleItem(
                    R.string.cat_color_role_on_background,
                    MaterialR.attr.colorOnBackground
                )
            ),
            ColorRow(
                ColorRoleItem(R.string.cat_color_role_surface, MaterialR.attr.colorSurface),
                ColorRoleItem(R.string.cat_color_role_on_surface, MaterialR.attr.colorOnSurface)
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_surface_variant,
                    MaterialR.attr.colorSurfaceVariant
                ),
                ColorRoleItem(
                    R.string.cat_color_role_on_surface_variant,
                    MaterialR.attr.colorOnSurfaceVariant
                )
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_inverse_surface,
                    MaterialR.attr.colorSurfaceInverse
                ),
                ColorRoleItem(
                    R.string.cat_color_role_inverse_on_surface,
                    MaterialR.attr.colorOnSurfaceInverse
                )
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_surface_bright,
                    MaterialR.attr.colorSurfaceBright
                ),
                ColorRoleItem(R.string.cat_color_role_surface_dim, MaterialR.attr.colorSurfaceDim)
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_surface_container_low,
                    MaterialR.attr.colorSurfaceContainerLow
                ),
                ColorRoleItem(
                    R.string.cat_color_role_surface_container_high,
                    MaterialR.attr.colorSurfaceContainerHigh
                )
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_surface_container_lowest,
                    MaterialR.attr.colorSurfaceContainerLowest
                ),
                ColorRoleItem(
                    R.string.cat_color_role_surface_container_highest,
                    MaterialR.attr.colorSurfaceContainerHighest
                )
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_surface_container,
                    MaterialR.attr.colorSurfaceContainer
                ),
                null
            )
        )
    }

    private fun getColorRolesContent(): List<ColorRow> {
        return listOf(
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_primary,
                    androidx.appcompat.R.attr.colorPrimary
                ),
                ColorRoleItem(R.string.cat_color_role_on_primary, MaterialR.attr.colorOnPrimary)
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_primary_container,
                    MaterialR.attr.colorPrimaryContainer
                ),
                ColorRoleItem(
                    R.string.cat_color_role_on_primary_container,
                    MaterialR.attr.colorOnPrimaryContainer
                )
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_primary_fixed,
                    MaterialR.attr.colorPrimaryFixed
                ),
                ColorRoleItem(
                    R.string.cat_color_role_primary_fixed_dim,
                    MaterialR.attr.colorPrimaryFixedDim
                )
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_on_primary_fixed,
                    MaterialR.attr.colorOnPrimaryFixed
                ),
                ColorRoleItem(
                    R.string.cat_color_role_on_primary_fixed_variant,
                    MaterialR.attr.colorOnPrimaryFixedVariant
                )
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_inverse_primary,
                    MaterialR.attr.colorPrimaryInverse
                ),
                null
            ),
            ColorRow(
                ColorRoleItem(R.string.cat_color_role_secondary, MaterialR.attr.colorSecondary),
                ColorRoleItem(R.string.cat_color_role_on_secondary, MaterialR.attr.colorOnSecondary)
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_secondary_container,
                    MaterialR.attr.colorSecondaryContainer
                ),
                ColorRoleItem(
                    R.string.cat_color_role_on_secondary_container,
                    MaterialR.attr.colorOnSecondaryContainer
                )
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_secondary_fixed,
                    MaterialR.attr.colorSecondaryFixed
                ),
                ColorRoleItem(
                    R.string.cat_color_role_secondary_fixed_dim,
                    MaterialR.attr.colorSecondaryFixedDim
                )
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_on_secondary_fixed,
                    MaterialR.attr.colorOnSecondaryFixed
                ),
                ColorRoleItem(
                    R.string.cat_color_role_on_secondary_fixed_variant,
                    MaterialR.attr.colorOnSecondaryFixedVariant
                )
            ),
            ColorRow(
                ColorRoleItem(R.string.cat_color_role_tertiary, MaterialR.attr.colorTertiary),
                ColorRoleItem(R.string.cat_color_role_on_tertiary, MaterialR.attr.colorOnTertiary)
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_tertiary_container,
                    MaterialR.attr.colorTertiaryContainer
                ),
                ColorRoleItem(
                    R.string.cat_color_role_on_tertiary_container,
                    MaterialR.attr.colorOnTertiaryContainer
                )
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_tertiary_fixed,
                    MaterialR.attr.colorTertiaryFixed
                ),
                ColorRoleItem(
                    R.string.cat_color_role_tertiary_fixed_dim,
                    MaterialR.attr.colorTertiaryFixedDim
                )
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_on_tertiary_fixed,
                    MaterialR.attr.colorOnTertiaryFixed
                ),
                ColorRoleItem(
                    R.string.cat_color_role_on_tertiary_fixed_variant,
                    MaterialR.attr.colorOnTertiaryFixedVariant
                )
            )
        )
    }

    private fun getColorRolesUtility(): List<ColorRow> {
        return listOf(
            ColorRow(
                ColorRoleItem(R.string.cat_color_role_error, androidx.appcompat.R.attr.colorError),
                ColorRoleItem(R.string.cat_color_role_on_error, MaterialR.attr.colorOnError)
            ),
            ColorRow(
                ColorRoleItem(
                    R.string.cat_color_role_error_container,
                    MaterialR.attr.colorErrorContainer
                ),
                ColorRoleItem(
                    R.string.cat_color_role_on_error_container,
                    MaterialR.attr.colorOnErrorContainer
                )
            ),
            ColorRow(
                ColorRoleItem(R.string.cat_color_role_outline, MaterialR.attr.colorOutline),
                ColorRoleItem(
                    R.string.cat_color_role_outline_variant,
                    MaterialR.attr.colorOutlineVariant
                )
            )
        )
    }
}