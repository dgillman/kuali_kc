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
package org.kuali.kra.iacuc.actions.reviewcomments;

import java.util.List;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolReviewAttachment;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsServiceImpl;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachment;

public class IacucReviewCommentsServiceImpl extends ReviewCommentsServiceImpl implements IacucReviewCommentsService {
    

    public void saveReviewAttachments(List<ProtocolReviewAttachment> reviewAttachments, List<ProtocolReviewAttachment> deletedReviewAttachments) {
        for (ProtocolReviewAttachment reviewAttachment : reviewAttachments) {
            boolean doUpdate = true;
//            if (reviewAttachment.getReviewerAttachmentId() != null) {
//                ProtocolOnlineReviewAttachment existing = committeeScheduleService.getCommitteeScheduleMinute(reviewAttachment.getCommScheduleMinutesId());
//                doUpdate = !reviewAttachment.equals(existing);
//            }
            if (doUpdate) {
                reviewAttachment.setPrivateFlag(!reviewAttachment.isProtocolPersonCanView());
                businessObjectService.save(reviewAttachment);
            }
        }
        
        if (!deletedReviewAttachments.isEmpty()) {
//            for (ProtocolReviewAttachment reviewAttachment : deletedReviewAttachments) {
//                businessObjectService.delete((IacucProtocolReviewAttachment)reviewAttachment);
//            }
            // TODO : bos expecting the object defined in repository
            businessObjectService.delete(deletedReviewAttachments);
        }
    }

    @Override
    protected ProtocolSubmission getSubmission(Protocol protocol) {
          ProtocolSubmission protocolSubmission = protocol.getProtocolSubmission();
          if (protocol.getNotifyIrbSubmissionId() != null) {
              // not the current submission, then check programically
              for (ProtocolSubmission submission : protocol.getProtocolSubmissions()) {
                  if (submission.getSubmissionId().equals(protocol.getNotifyIrbSubmissionId())) {
                      protocolSubmission = submission;
                      break;
                  }
              }
          }
          return protocolSubmission;
    }

    @Override
    protected Class<? extends ProtocolReviewAttachment> getProtocolReviewAttachmentClassHook() {
        return IacucProtocolReviewAttachment.class;
    }



}