package org.example.prospero.data.services.zipcode;

import java.util.List;

public interface ZipcodeService {

	List<String> fetchZipcodes(String city);
}
