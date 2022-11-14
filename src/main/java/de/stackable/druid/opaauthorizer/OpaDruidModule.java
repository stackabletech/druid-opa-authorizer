package de.stackable.druid.opaauthorizer;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.ImmutableList;
import com.google.inject.Binder;
import org.apache.druid.initialization.DruidModule;

import java.util.List;

public class OpaDruidModule implements DruidModule {

    @Override
    public void configure(Binder binder) {
    }

    @Override
    public List<? extends Module> getJacksonModules() {
        return ImmutableList.of(
            new SimpleModule("Opa").registerSubtypes(
                    OpaAuthorizer.class
            )
        );
    }
}
