/*******************************************************************************
 * Copyright (c) 2011 Daniel Murygin.
 *
 * This program is free software: you can redistribute it and/or 
 * modify it under the terms of the GNU Lesser General Public License 
 * as published by the Free Software Foundation, either version 3 
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,    
 * but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. 
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 *     Daniel Murygin <dm[at]sernet[dot]de> - initial API and implementation
 ******************************************************************************/
package org.n2.app.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.n2.app.persistence.User;
import org.n2.app.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService implements IUserService, Serializable {
    
    private static final Logger LOG = Logger.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;
    
    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public User findUser(String username) {

        List<User> result = userRepository.findByLogin(username);
        User user = null;
        if(result!=null) {
            if(result.size()>1) {
                LOG.error("More than one user found with login: " + username);
                throw new RuntimeException("More than one user found.");
            }
            if(!result.isEmpty()) {
                user = result.get(0);
            }
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUsernameAvailable(String username) {
        List<User> userList = userRepository.findByLogin(username);
        return userList==null || userList.isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailAvailable(String email) {
        List<User> userList = userRepository.findByEmail(email);
        return userList==null || userList.isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> listUsers(){
        return userRepository.findAll();
    }

}
