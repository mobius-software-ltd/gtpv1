package com.mobius.software.telco.protocols.gtp.api.headers.v2;
/*Mobius Software LTD
Copyright 2019, Mobius Software LTD and individual contributors
by the @authors tag.

This program is free software: you can redistribute it and/or modify
under the terms of the GNU Affero General Public License as
published by the Free Software Foundation; either version 3 of
the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>*/
import com.mobius.software.telco.protocols.gtp.api.headers.PriorityLevel;

public interface BearerQos extends TLV2
{
	Boolean getPVI();
	
	void setPVI(Boolean value);
	
	Boolean getPCI();
	
	void setPCI(Boolean value);
	
	PriorityLevel getLevel();
	
	void setLevel(PriorityLevel level);
	
	Integer getLabel();
	
	void setLabel(Integer label);	
	
	Long getMaximumBitrateforUL();
	
	void setMaximumBitrateforUL(Long value);
	
	Long getMaximumBitrateforDL();
	
	void setMaximumBitrateforDL(Long value);
	
	Long getGuaranteedBitrateforUL();
	
	void setGuaranteedBitrateforUL(Long value);
	
	Long getGuaranteedBitrateforDL();	
	
	void setGuaranteedBitrateforDL(Long value);
}