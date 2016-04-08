package com.github.leichtundkross.jee7.cdi.veto;

import javax.enterprise.inject.Vetoed;

/**
 * Annotation {@link Vetoed} will disable this class
 * <ul>
 * <li>to become a CDI managed bean</li>
 * <li>to become an EJB managed bean</li>
 * <li>to can be fired as a CDI event</li>
 * </ul>
 */
@Vetoed
public class MyImpl2 implements MyInterface {

}
