/*
 * Aipo is a groupware program developed by Aimluck,Inc.
 * Copyright (C) 2004-2011 Aimluck,Inc.
 * http://www.aipo.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.aimluck.eip.modules.screens;

import org.apache.jetspeed.services.logging.JetspeedLogFactoryService;
import org.apache.jetspeed.services.logging.JetspeedLogger;
import org.apache.turbine.util.RunData;

import com.aimluck.eip.cayenne.om.portlet.EipTMessageFile;
import com.aimluck.eip.common.ALPermissionException;
import com.aimluck.eip.message.util.MessageUtils;
import com.aimluck.eip.orm.Database;

/**
 *
 */
public class MessageFileRawScreen extends FileuploadRawScreen {

  /** logger */
  private static final JetspeedLogger logger = JetspeedLogFactoryService
    .getLogger(MessageFileRawScreen.class.getName());

  /**
   * 
   * @param rundata
   * @throws Exception
   */
  @Override
  protected void doOutput(RunData rundata) throws Exception {
    try {
      EipTMessageFile file = MessageUtils.getEipTMessageFile(rundata);

      super.setFilePath(MessageUtils.getSaveDirPath(
        Database.getDomainName(),
        file.getOwnerId().intValue())
        + file.getFilePath());
      super.setFileName(file.getFileName());
      super.doOutput(rundata);
    } catch (ALPermissionException e) {
      throw new Exception();
    } catch (Exception e) {
      logger.error("MessageFileRawScreen.doOutput", e);
    }
  }

}
