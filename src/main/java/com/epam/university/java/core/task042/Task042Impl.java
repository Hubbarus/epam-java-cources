package com.epam.university.java.core.task042;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Task042Impl implements Task042 {
    private static final LocalTime START = LocalTime.of(9, 0);
    private static final LocalTime END = LocalTime.of(18, 0);

    @Override
    public BookingResponse checkAvailability(List<String> schedule, String currentTime) {
        if (currentTime == null || schedule == null) {
            throw new IllegalArgumentException();
        }

        LocalTime current = LocalTime.parse(currentTime);

        BookingResponse timeProposalResponse = getBookingResponse(schedule, current);
        if (timeProposalResponse != null) {
            return timeProposalResponse;
        }

        for (int i = 0; i < schedule.size(); i++) {

            String[] split = schedule.get(i).split("-");
            if (split.length != 2) {
                throw new IllegalArgumentException();
            }

            LocalTime busyStart = LocalTime.parse(split[0]);
            LocalTime busyEnd = LocalTime.parse(split[1]);
            if (current.compareTo(busyStart) == 0) {
                return new BusyResponse();
            }

            if (current.isAfter(busyStart) && current.isBefore(busyEnd)) {
                long toEnd = ChronoUnit.MINUTES.between(current, busyEnd);
                long fromStart = busyStart.until(current, ChronoUnit.MINUTES);

                if (toEnd <= fromStart) {
                    TimeProposalResponse timeProposalResponse1 = new TimeProposalResponse();
                    String endTime = getEndTime(schedule);
                    timeProposalResponse1.setProposedTime(endTime);
                    return timeProposalResponse1;
                } else {
                    return new BusyResponse();
                }
            }
        }
        return new AvailableResponse();
    }

    private String getEndTime(List<String> schedule) {
        String[] strings = new String[schedule.size()];
        schedule.toArray(strings);
        for (int i = 0; i < strings.length; i++) {
            if (i != strings.length - 1) {
                String[] split = strings[i].split("-");
                if (strings[i + 1].startsWith(split[1])) {
                    String[] split1 = strings[i + 1].split("-");
                    strings[i + 1] = split[0] + "-" + split1[1];
                    strings[i] = "";
                } else {
                    return split[1];
                }
            }
        }
        String[] split = strings[strings.length - 1].split("-");
        return split[1];
    }

    private BookingResponse getBookingResponse(List<String> schedule, LocalTime current) {
        if (current.compareTo(END) == 0) {
            return new BusyResponse();
        }

        if (current.isBefore(START) || current.isAfter(END)) {
            TimeProposalResponse timeProposalResponse = new TimeProposalResponse();
            timeProposalResponse.setProposedTime(START.toString());
            return timeProposalResponse;
        }

        if (schedule.isEmpty()) {
            return new AvailableResponse();
        }
        return null;
    }
}
