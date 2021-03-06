/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.internal.notations;

import org.gradle.api.InvalidUserDataException;
import org.gradle.api.artifacts.ClientModule;
import org.gradle.api.artifacts.ExternalDependency;
import org.gradle.api.internal.artifacts.dsl.ParsedModuleStringNotation;
import org.gradle.api.internal.artifacts.dsl.dependencies.ModuleFactoryHelper;
import org.gradle.internal.exceptions.DiagnosticsVisitor;
import org.gradle.internal.reflect.Instantiator;
import org.gradle.internal.typeconversion.NotationConvertResult;
import org.gradle.internal.typeconversion.NotationConverter;
import org.gradle.internal.typeconversion.TypeConversionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DependencyStringNotationConverter<T extends ExternalDependency> implements NotationConverter<String, T> {
    private final Instantiator instantiator;
    private final Class<T> wantedType;

    public DependencyStringNotationConverter(Instantiator instantiator, Class<T> wantedType) {
        this.instantiator = instantiator;
        this.wantedType = wantedType;
    }

    @Override
    public void describe(DiagnosticsVisitor visitor) {
        visitor.candidate("String or CharSequence values").example("'org.gradle:gradle-core:1.0'");
    }

    public void convert(String notation, NotationConvertResult<? super T> result) throws TypeConversionException {
        result.converted(createDependencyFromString(notation));
    }

    public static final Pattern EXTENSION_SPLITTER = Pattern.compile("^(.+)\\@([^:]+$)");

    private T createDependencyFromString(String notation) {

        ParsedModuleStringNotation parsedNotation = splitModuleFromExtension(notation);
        T moduleDependency = instantiator.newInstance(wantedType,
                parsedNotation.getGroup(), parsedNotation.getName(), parsedNotation.getVersion());
        ModuleFactoryHelper.addExplicitArtifactsIfDefined(moduleDependency, parsedNotation.getArtifactType(), parsedNotation.getClassifier());

        return moduleDependency;
    }

    private ParsedModuleStringNotation splitModuleFromExtension(String notation) {
        Matcher matcher = EXTENSION_SPLITTER.matcher(notation);
        boolean hasArtifactType = matcher.matches();
        if (hasArtifactType && !ClientModule.class.isAssignableFrom(wantedType)) {
            if (matcher.groupCount() != 2) {
                throw new InvalidUserDataException("The dependency notation " + notation + " is invalid");
            }
            return new ParsedModuleStringNotation(matcher.group(1), matcher.group(2));
        }
        return new ParsedModuleStringNotation(notation, null);
    }
}
