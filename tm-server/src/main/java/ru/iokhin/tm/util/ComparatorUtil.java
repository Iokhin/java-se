package ru.iokhin.tm.util;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entityDTO.ProjectDTO;
import ru.iokhin.tm.entityDTO.TaskDTO;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ComparatorUtil {
    public static Comparator<ProjectDTO> getProjectComparator(@NotNull final String comparator) {
        Comparator<ProjectDTO> dateStartComparator = (o1, o2) -> {
            Date date1 = o1.getStartDate();
            Date date2 = o2.getStartDate();
            if (date1 == null) date1 = new Date(Long.MAX_VALUE);
            if (date2 == null) date2 = new Date(Long.MAX_VALUE);
            return date1.compareTo(date2);
        };

        Comparator<ProjectDTO> dateEndComparator = (o1, o2) -> {
            Date date1 = o1.getEndDate();
            Date date2 = o2.getEndDate();
            if (date1 == null) date1 = new Date(Long.MAX_VALUE);
            if (date2 == null) date2 = new Date(Long.MAX_VALUE);
            return date1.compareTo(date2);
        };

        Comparator<ProjectDTO> statusComparator = Comparator.comparing(ProjectDTO::getStatus);

        Map<String, Comparator<ProjectDTO>> comparatorMap = new HashMap<>(0);
        comparatorMap.put("dateStart", dateStartComparator);
        comparatorMap.put("dateEnd", dateEndComparator);
        comparatorMap.put("status", statusComparator);
        return comparatorMap.get(comparator);
    }

    public static Comparator<TaskDTO> getTaskComparator(@NotNull final String comparator) {
        Comparator<TaskDTO> dateStartComparator = (o1, o2) -> {
            Date date1 = o1.getStartDate();
            Date date2 = o2.getStartDate();
            if (date1 == null) date1 = new Date(Long.MAX_VALUE);
            if (date2 == null) date2 = new Date(Long.MAX_VALUE);
            return date1.compareTo(date2);
        };

        Comparator<TaskDTO> dateEndComparator = (o1, o2) -> {
            Date date1 = o1.getEndDate();
            Date date2 = o2.getEndDate();
            if (date1 == null) date1 = new Date(Long.MAX_VALUE);
            if (date2 == null) date2 = new Date(Long.MAX_VALUE);
            return date1.compareTo(date2);
        };

        Comparator<TaskDTO> statusComparator = Comparator.comparing(TaskDTO::getStatus);

        Map<String, Comparator<TaskDTO>> comparatorMap = new HashMap<>(0);
        comparatorMap.put("dateStart", dateStartComparator);
        comparatorMap.put("dateEnd", dateEndComparator);
        comparatorMap.put("status", statusComparator);
        return comparatorMap.get(comparator);
    }
}
