/*
 * Copyright 2005 Davy Verstappen.
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
package org.jsmiparser.smi;

import org.apache.log4j.Logger;
import org.jsmiparser.util.problem.DefaultProblemHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// TODO remove class, after moving part to ConceptualModelBuilderPhase
public abstract class SmiAbstractParser implements SmiParser {

    private static final Logger m_log = Logger.getLogger(SmiAbstractParser.class);

	protected DefaultProblemHandler eh_;
	protected SmiCodeNamingStrategy codeNamingStrategy_;
	private List<File> classDefinitionFiles_ = new ArrayList<File>();

	public SmiAbstractParser(DefaultProblemHandler eh, SmiCodeNamingStrategy strategy) {
		super();
		eh_ = eh;
		codeNamingStrategy_ = strategy;
	}

	public SmiAbstractParser() {
		super();
	}

	public final SmiMib parse() throws IOException, SAXException {
		SmiMib mib = parseBasics();
		

		
		return mib;
	}




	protected abstract SmiMib parseBasics() throws IOException;

	public DefaultProblemHandler getErrorHandler() {
		return eh_;
	}

	public void addClassDefinitionFile(File f) {
		classDefinitionFiles_.add(f);
	}


}
