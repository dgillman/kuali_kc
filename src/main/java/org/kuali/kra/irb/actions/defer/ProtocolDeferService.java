/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.defer;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;

/**
 * Protocol Withdraw Service.
 */
public interface ProtocolDeferService {

    /**
     * Perform the task of deferring a protocol.  A new protocol document will be created
     * so that it can be re-submitted at a later time.
     * @param protocol the protocol
     * @param deferBean the required data for performing a deferral
     * @return new protocol document 
     * @throws Exception 
     */
    public ProtocolDocument defer(Protocol protocol, ProtocolGenericActionBean deferBean) throws Exception;
}
