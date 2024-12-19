package org.example;

import org.apache.maven.api.services.SettingsBuilderRequest;
import org.apache.maven.eventspy.EventSpy;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Named("simple")
@Singleton
public class SimpleEventSpy implements EventSpy {
    private final List<Object> events = new ArrayList<>();

    @Override
    public void init(Context context) throws Exception {
        System.out.println("Initializing Simple Event Spy");
    }

    @Override
    public void onEvent(Object o) throws Exception {
        events.add(o);
        //System.out.println("Event: " + o);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing Simple Event Spy, checking SettingsBuilderResult event");
        events.stream().filter(e -> e instanceof SettingsBuilderRequest).findAny().orElseThrow();
    }

}