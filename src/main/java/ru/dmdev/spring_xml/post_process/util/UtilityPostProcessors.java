package ru.dmdev.spring_xml.post_process.util;

import org.springframework.beans.factory.config.ConstructorArgumentValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class UtilityPostProcessors {

    private static final Map<String, String> propertyMap = new ConcurrentHashMap<>();
    private static final String PREFIX = "~[";
    private static final String SUFFIX = "]";
    private static final String SEPARATOR = "=";

    protected void initPropertyMap(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int[] i = new int[]{0};
            propertyMap.putAll(
                    reader.lines().collect(Collectors.toMap(
                    k -> k.substring(0, i[0] = k.indexOf(SEPARATOR)),
                    v -> v.substring(++i[0])))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void genericArgumentsValuesChanges(
            List<ConstructorArgumentValues.ValueHolder> genericArgumentValues) {

        for (ConstructorArgumentValues.ValueHolder valueHolder : genericArgumentValues) {
            changeFieldsByProperties(valueHolder);
        }
    }

    protected void indexedArgumentValuesChanges(
            Map<Integer, ConstructorArgumentValues.ValueHolder> indexedArgumentValues) {

        for (ConstructorArgumentValues.ValueHolder valueHolder : indexedArgumentValues.values()) {
            changeFieldsByProperties(valueHolder);
        }
    }

    private void changeFieldsByProperties(ConstructorArgumentValues.ValueHolder valueHolder) {
        String string = valueHolder.getValue().toString()
                .replaceAll("TypedStringValue: value \\[|], target type \\[null]", "");

        if (string.startsWith(PREFIX) && string.endsWith(SUFFIX)) {
            String props;
            if ((props = propertyMap.get(string.substring(2, string.length() - 1))) != null) {
                valueHolder.setValue(props);
            }
        }
    }

    protected String newValue(String oldValue) {
        if (oldValue.startsWith(PREFIX) && oldValue.endsWith(SUFFIX)) {
            return propertyMap.get(oldValue.substring(2, oldValue.length() - 1));
        }
        return oldValue;
    }
}
