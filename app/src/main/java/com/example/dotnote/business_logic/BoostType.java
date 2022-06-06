package com.example.dotnote.business_logic;

/**
 * Enum representing the various help boosts and their associated costs
 */
public enum BoostType {
    FIFTY_FIFTY,
    CALLER_HELP,
    NEW_QUESTION,
    SPECTATOR_POLL;

    public static final int[] costs = { 100, 80, 60, 70 };


}
