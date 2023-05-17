package org.example.agile.poker;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "org.example.agile.poker")
public class ArchitectureLayerTest {

    @ArchTest
    public static final ArchRule withLayersTest = layeredArchitecture().consideringOnlyDependenciesInLayers()
            .layer("Controller").definedBy("..controller..")
            .layer("Service").definedBy("..service..")
            .layer("Persistence").definedBy("..persistence..")

            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Persistence").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayNotAccessAnyLayer();
}
