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
package org.kuali.kra.common.specialreview.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.bo.SpecialReviewUsage;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder;
import org.kuali.rice.kns.lookup.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * Provides a value finder for module-specific configuration of Special Review Types.
 */
public abstract class SpecialReviewTypeValuesFinder extends KeyValuesBase {

    private static final String SPECIAL_REVIEW_TYPE_CODE_NAME = "specialReviewTypeCode";
    private static final String SPECIAL_REVIEW_TYPE_CODE_DESCRIPTION = "description";
    private static final String MODULE_CODE_NAME = "moduleCode";
    
    private BusinessObjectService businessObjectService;
    
    /**
     * {@inheritDoc}
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<?> getKeyValues() {
        @SuppressWarnings("unchecked")
        final List<KeyLabelPair> keyValues = createKeyValuesFinder().getKeyValues();
        
        return filterActiveSpecialReviewUsageTypes(keyValues);
    }
    
    private KeyValuesFinder createKeyValuesFinder() {
        PersistableBusinessObjectValuesFinder valuesFinder = new PersistableBusinessObjectValuesFinder();
        valuesFinder.setBusinessObjectClass(SpecialReviewType.class);
        valuesFinder.setKeyAttributeName(SPECIAL_REVIEW_TYPE_CODE_NAME);
        valuesFinder.setLabelAttributeName(SPECIAL_REVIEW_TYPE_CODE_DESCRIPTION);
        return new PrefixValuesFinder(valuesFinder);
    }
    
    private List<KeyLabelPair> filterActiveSpecialReviewUsageTypes(List<KeyLabelPair> unfilteredKeyValues) {
        final List<KeyLabelPair> filteredKeyValues = new ArrayList<KeyLabelPair>();
        
        Collection<SpecialReviewUsage> specialReviewUsages = getSpecialReviewUsages();
        for (KeyLabelPair item : unfilteredKeyValues) {
            SpecialReviewUsage itemSpecialReviewUsage = null;
            for (SpecialReviewUsage specialReviewUsage : specialReviewUsages) {
                if (StringUtils.equals(specialReviewUsage.getSpecialReviewTypeCode(), String.valueOf(item.getKey()))) {
                    itemSpecialReviewUsage = specialReviewUsage;
                    break;
                }
            }
            if (itemSpecialReviewUsage == null || itemSpecialReviewUsage.isActive()) {
                filteredKeyValues.add(item);
            }
        }
        
        return filteredKeyValues;
    }
    
    /**
     * Gets the Coeus Module code for the module using this value finder.
     * @return the Coeus Module code
     */
    public abstract String getModuleCode();
    
    @SuppressWarnings("unchecked")
    private Collection<SpecialReviewUsage> getSpecialReviewUsages() {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(MODULE_CODE_NAME, getModuleCode());
        
        return getBusinessObjectService().findMatching(SpecialReviewUsage.class, fieldValues);
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}