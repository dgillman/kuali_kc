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
package org.kuali.kra.irb.actions.delete;

import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionBean;

/**
 * This class is really just a "form" containing the reason
 * for deleting a protocol/amendment/renewal.
 */
public class ProtocolDeleteBean extends ProtocolActionBean implements org.kuali.kra.protocol.actions.delete.ProtocolDeleteBean {

    private static final long serialVersionUID = 7654109710201779704L;
    
    private String reason = "";
    
    /**
     * Constructs a ProtocolDeleteBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolDeleteBean(ActionHelper actionHelper) {
        super(actionHelper);
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
