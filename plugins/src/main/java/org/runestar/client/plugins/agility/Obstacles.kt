package org.runestar.client.plugins.agility

import com.google.common.collect.ImmutableSet
import org.runestar.client.game.api.ObjectId.*

/*
 * Copyright (c) 2018, SomeoneWithAnInternetConnection
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
val OBSTACLE_IDS: Set<Int> = ImmutableSet.of(
        // Gnome
        OBSTACLE_NET_23134, TREE_BRANCH_23559, TREE_BRANCH_23560, OBSTACLE_NET_23135, OBSTACLE_PIPE_23138,
        OBSTACLE_PIPE_23139, LOG_BALANCE_23145, BALANCING_ROPE_23557,
        // Brimhaven
        PLANK_3572, PLANK_3571, PLANK_3570, ROPE_SWING_3566, PILLAR_3578, LOW_WALL_3565, LOG_BALANCE_3553, LOG_BALANCE_3557,
        BALANCING_LEDGE_3561, BALANCING_LEDGE_3559, MONKEY_BARS_3564, BALANCING_ROPE_3551, HAND_HOLDS_3583,
        // Draynor
        ROUGH_WALL_10073, TIGHTROPE_10074, TIGHTROPE_10075, NARROW_WALL_10077, WALL_10084, GAP_10085, CRATE_10086,
        // Al-Kharid
        ROUGH_WALL_10093, TIGHTROPE_10284, CABLE_10355, ZIP_LINE_10356, TROPICAL_TREE_10357, ROOF_TOP_BEAMS_10094,
        TIGHTROPE_10583, GAP_10352,
        // Pyramid
        STAIRS_10857, LOW_WALL_10865, LEDGE_10860, PLANK_10868, GAP_10882, LEDGE_10886, STAIRS_10857, GAP_10884,
        GAP_10859, GAP_10861, LOW_WALL_10865, GAP_10859, LEDGE_10888, PLANK_10868, CLIMBING_ROCKS_10851, DOORWAY_10855,
        // Varrock
        ROUGH_WALL_10586, CLOTHES_LINE_10587, GAP_10642, WALL_10777, GAP_10778, GAP_10779, GAP_10780, LEDGE_10781, EDGE_10817,
        // Penguin
        STEPPING_STONE_21120, STEPPING_STONE_21126, STEPPING_STONE_21128, STEPPING_STONE_21129,
        STEPPING_STONE_21130, STEPPING_STONE_21131, STEPPING_STONE_21132, STEPPING_STONE_21133,
        ICICLES_21134, ICE_21148, ICE_21149, ICE_21150, ICE_21151, ICE_21152, ICE_21153, ICE_21154, ICE_21155, ICE_21156,
        // Barbarian
        ROPESWING_23131, LOG_BALANCE_23144, OBSTACLE_NET_20211, BALANCING_LEDGE_23547, LADDER_16682, CRUMBLING_WALL_1948,
        // Canifis
        TALL_TREE_10819, GAP_10820, GAP_10821, GAP_10828, GAP_10822, POLE_VAULT_10831, GAP_10823, GAP_10832,
        // Ape atoll
        STEPPING_STONE_15412, TROPICAL_TREE_15414, MONKEYBARS_15417, SKULL_SLOPE_15483, ROPE_15487, TROPICAL_TREE_16062,
        // Falador
        ROUGH_WALL_10833, TIGHTROPE_10834, HAND_HOLDS_10836, GAP_11161, GAP_11360, TIGHTROPE_11361,
        TIGHTROPE_11364, GAP_11365, LEDGE_11366, LEDGE_11367, LEDGE_11368, LEDGE_11370, EDGE_11371,
        // Wilderness
        OBSTACLE_PIPE_23137, ROPESWING_23132, STEPPING_STONE_23556, LOG_BALANCE_23542, ROCKS_23640,
        // Seers
        WALL_11373, GAP_11374, TIGHTROPE_11378, GAP_11375, GAP_11376, EDGE_11377,
        // Dorgesh-Kaan
        CABLE_22569, CABLE_22572, LADDER_22564, JUTTING_WALL_22552, TUNNEL_22557, PYLON_22664,
        CONSOLE_22634, STAIRS_22650, STAIRS_22651, STAIRS_22609, STAIRS_22608,
        // Pollnivneach
        BASKET_11380, MARKET_STALL_11381, BANNER_11382, GAP_11383, TREE_11384, ROUGH_WALL_11385,
        MONKEYBARS_11386, TREE_11389, DRYING_LINE_11390,
        // Rellekka
        ROUGH_WALL_11391, GAP_11392, TIGHTROPE_11393, GAP_11395, GAP_11396, TIGHTROPE_11397, PILE_OF_FISH_11404,
        // Ardougne
        GAP_11406, GAP_11429, GAP_11430, STEEP_ROOF_11633, GAP_11630, PLANK_11631, WOODEN_BEAMS_11405
)