package com.nice.mcr.injector.policies;

import com.nice.mcr.injector.model.Agent;
import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.springframework.boot.ApplicationArguments;

import java.time.LocalTime;
import java.util.List;

public interface Policy {
    public void run();
    default void run(ApplicationArguments args) {}
    default void setApplicationArguments(ApplicationArguments args) {}

    /**
     * Use this method to generate the list of agents according to specifications/requirements.
     * @param numberOfAgents Number of agents names in the final list.
     * @param uniqueNamePercent Percentage of unique agents names, e.g.
     *                          value=66.5 = 66.5% means 33.5% (335 of 1000) of the names are repeating.
     *                          value=100.0 = 100% means all unique names, no duplicates.
     * @param db {@code MapDB} database ({@link DB}), {@code null} if not in use.
     * @return {@link HTreeMap}<{@link String}, {@link Agent}> containing the list of agent names as the
     * {@code key} and the value is the agent data in {@link Agent} class.
     * Note:
     *      We might need to add the number of times each name repeats
     *      in the list of agents names.
     * For Example:
     *      key="John Doe", value=3 means that the agent name "John Doe"
     *      appears 3 times in the agents list.
     */
    default HTreeMap<String, Agent> generateListOfAgents(int numberOfAgents, double uniqueNamePercent, DB db) {
        return null;
    }

    /**
     * Use this method to generate the list of calls per day according to specifications/requirements.
     * @param numberOfCallsPerDay Number of calls-per-day
     * @param durationOfCallInMinutes Duration of a single call in minutes, will not exceed value
     *                                of [int(1440/numberOfCallsPerDay) - 1] to avoid calls overlapping
     *                                or exceeding 24-hours limit. Note: 1440 = 24 hours x 60 minutes per hour.
     * @return {@link List} of {@link LocalTime} containing the times of the calls in "hh:mm:ss" 24-hours time format.
     */
    default List<LocalTime> generateListOfCallsPerDay(int numberOfCallsPerDay, int durationOfCallInMinutes) {
        return null;
    }
}
