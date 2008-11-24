/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.rule;

import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.award.rule.event.AddAwardIndirectCostRateEvent;

/**
 * 
 * This interface declares the rule method associated with <code>AwardIndirectCostRate</code> Business Object.
 */
public interface AddIndirectCostRateRule extends BusinessRule {
    
    /**
     * Rule invoked upon adding a indirect cost rate
     * <code>{@link org.kuali.kra.award.document.AwardDocument}</code>
     *
     * @return boolean
     */
    public boolean processAddIndirectCostRatesBusinessRules(AddAwardIndirectCostRateEvent addAwardIndirectCostRateEvent);

}