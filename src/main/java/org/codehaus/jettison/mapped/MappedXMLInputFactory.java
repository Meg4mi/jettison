/**
 * Copyright 2006 Envoi Solutions LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.jettison.mapped;

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.jettison.AbstractXMLInputFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MappedXMLInputFactory extends AbstractXMLInputFactory {


    private MappedNamespaceConvention convention;

    public MappedXMLInputFactory(Map nstojns) {
        this.convention = new MappedNamespaceConvention(nstojns);
    }
    
    public XMLStreamReader createXMLStreamReader(JSONTokener tokener) throws XMLStreamException {
        try {
            JSONObject root = new JSONObject(tokener);
            return new MappedXMLStreamReader(root, convention);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new XMLStreamException(e);
        }
    }
}
