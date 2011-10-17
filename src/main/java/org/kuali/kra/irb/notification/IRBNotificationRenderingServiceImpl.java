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
package org.kuali.kra.irb.notification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class IRBNotificationRenderingServiceImpl implements IRBNotificationRenderingService {

    private Protocol protocol;
    private BusinessObjectService businessObjectService;
    
    
    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationRenderingService#render(java.lang.String)
     */
    @Override
    public String render(String text) {
        return render(text, getReplacementParameters());
    }
    
    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationRenderingService#render(java.lang.String, java.util.Map)
     */
    @Override
    public String render(String text, Map<String,String> replacementParameters) { 
        for (String key : replacementParameters.keySet()) {
            text = StringUtils.replace(text, key, replacementParameters.get(key));
        }
        
        return text;
    }

    /**
     * 
     * @see org.kuali.kra.common.notification.service.KcNotificationRenderingService#getReplacementParameters()
     */
    @Override
    public Map<String, String> getReplacementParameters() {
        String[] replacementParameters = IRBReplacementParameters.REPLACEMENT_PARAMETERS;
        
        Map<String, String> params = new HashMap<String, String>();
        
        String key = null;
        for (int i = 0; i < replacementParameters.length; i++) {
            key = replacementParameters[i];
            if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_NUMBER)) {
                params.put(key, protocol.getProtocolNumber());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PI_NAME)) {
                params.put(key, protocol.getPrincipalInvestigator().getFullName());
            } else if (StringUtils.equals(key, IRBReplacementParameters.LEAD_UNIT)) {
                params.put(key, protocol.getLeadUnitNumber());
            } else if (StringUtils.equals(key, IRBReplacementParameters.LEAD_UNIT_NAME)) {
                params.put(key, protocol.getLeadUnitName());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_STATUS_CODE)) {
                params.put(key, protocol.getProtocolStatusCode());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_STATUS_DESCRIPTION)) {
                params.put(key, protocol.getProtocolStatus().getDescription());
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_ACTION_NAME)) {
                if (protocol.getLastProtocolAction() != null) {
                    params.put(key, getProtocolLastActionName(protocol.getLastProtocolAction().getProtocolActionTypeCode()));    
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_ACTION_TYPE_CODE)) {
                if (protocol.getLastProtocolAction() != null) {
                    params.put(key, protocol.getLastProtocolAction().getProtocolActionTypeCode());
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_NAME)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, getProtocolSubmissionName(protocol.getProtocolSubmission().getSubmissionTypeCode()));
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_TYPE_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionTypeCode());
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionTypeQualifierCode());
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.LAST_SUBMISSION_TYPE_QUAL_NAME)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, getLastSubmissionTypeQualifierName(protocol.getProtocolSubmission().getSubmissionTypeQualifierCode()));
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_TITLE)) {
                params.put(key, protocol.getTitle());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_TYPE_CODE)) {
                params.put(key, protocol.getProtocolTypeCode());
            } else if (StringUtils.equals(key, IRBReplacementParameters.PROTOCOL_TYPE_DESCRIPTION)) {
                if (protocol.getProtocolType() != null) {
                    params.put(key, protocol.getProtocolType().getDescription());
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.SEQUENCE_NUMBER)) {
                params.put(key, protocol.getSequenceNumber().toString());
            } else if (StringUtils.equals(key, IRBReplacementParameters.SUBMISSION_STATUS_CODE)) {
                if (protocol.getProtocolSubmission() != null) {
                    params.put(key, protocol.getProtocolSubmission().getSubmissionStatusCode());
                }
            } else if (StringUtils.equals(key, IRBReplacementParameters.SUBMISSION_STATUS_NAME)) {
                params.put(key, protocol.getProtocolSubmissionStatus());
            } else if (StringUtils.equals(key, IRBReplacementParameters.DOCUMENT_NUMBER)) {
                params.put(key, protocol.getProtocolDocument().getDocumentNumber());
            }
        }
        
        return params;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KNSServiceLocator.getBusinessObjectService();
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }


    private String getProtocolLastActionName(String lastActionTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolActionTypeCode", lastActionTypeCode);
        List<ProtocolActionType> actionTypes = (List<ProtocolActionType>) getBusinessObjectService().findMatching(ProtocolActionType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(actionTypes)) {
            result = actionTypes.get(0).getDescription();
        }
        
        return result;
    }

    private String getProtocolSubmissionName(String submissionTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("submissionTypeCode", submissionTypeCode);
        List<ProtocolSubmissionType> submissionTypes = 
            (List<ProtocolSubmissionType>) getBusinessObjectService().findMatching(ProtocolSubmissionType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(submissionTypes)) {
            result = submissionTypes.get(0).getDescription();
        }
        
        return result;
    }
    
    private String getLastSubmissionTypeQualifierName(String submissionQualifierTypeCode) {
        String result = null;
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("submissionQualifierTypeCode", submissionQualifierTypeCode);
        List<ProtocolSubmissionQualifierType> submissionQualifierTypes = 
            (List<ProtocolSubmissionQualifierType>) getBusinessObjectService().findMatching(ProtocolSubmissionQualifierType.class, fieldValues);
        if (CollectionUtils.isNotEmpty(submissionQualifierTypes)) {
            result = submissionQualifierTypes.get(0).getDescription();
        }
        
        return result;
    }

}