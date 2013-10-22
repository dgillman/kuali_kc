/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.personnel;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;


public class IacucProtocolPerson extends ProtocolPersonBase {

    private static final long serialVersionUID = 6676849646094141708L;
    private String procedureQualificationDescription;
    private List<IacucProcedurePersonResponsible> procedureDetails;
    
    public IacucProtocolPerson() {
        super();
        setProcedureDetails(new ArrayList<IacucProcedurePersonResponsible>());
    }
    
    public IacucProtocol getIacucProtocol() {
        return (IacucProtocol) getProtocol();
    }

    public String getProcedureQualificationDescription() {
        return procedureQualificationDescription;
    }

    public void setProcedureQualificationDescription(String procedureQualificationDescription) {
        this.procedureQualificationDescription = procedureQualificationDescription;
    }

    public List<IacucProcedurePersonResponsible> getProcedureDetails() {
        return procedureDetails;
    }

    public void setProcedureDetails(List<IacucProcedurePersonResponsible> procedureDetails) {
        this.procedureDetails = procedureDetails;
    }

}
