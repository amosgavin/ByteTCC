/**
 * Copyright 2014-2017 yangming.liu<bytefox@126.com>.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, see <http://www.gnu.org/licenses/>.
 */
package org.bytesoft.bytetcc.supports.springcloud;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.bytesoft.bytejta.supports.wire.RemoteCoordinator;
import org.bytesoft.compensable.CompensableBeanFactory;
import org.bytesoft.compensable.aware.CompensableBeanFactoryAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CompensableBeanRegistry implements CompensableBeanFactoryAware {
	static final Logger logger = LoggerFactory.getLogger(CompensableBeanRegistry.class);
	private static final CompensableBeanRegistry instance = new CompensableBeanRegistry();

	private CompensableBeanFactory beanFactory;
	private RemoteCoordinator consumeCoordinator;

	private Lock lock = new ReentrantLock();
	private Condition condition = this.lock.newCondition();

	private CompensableBeanRegistry() {
		if (instance != null) {
			throw new IllegalStateException();
		}
	}

	public static CompensableBeanRegistry getInstance() {
		return instance;
	}

	public RemoteCoordinator getConsumeCoordinator() {
		return this.consumeCoordinator;
	}

	public void setConsumeCoordinator(RemoteCoordinator consumeCoordinator) {
		this.consumeCoordinator = consumeCoordinator;
	}

	public void setBeanFactory(CompensableBeanFactory tbf) {
		this.beanFactory = tbf;
	}

	public CompensableBeanFactory getBeanFactory() {
		return beanFactory;
	}

}