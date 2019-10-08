package bzh.ariad.checker.service;

import java.util.List;

public interface CheckUserService {

	public boolean isValid(String userId, List<String> lines);
	
}
