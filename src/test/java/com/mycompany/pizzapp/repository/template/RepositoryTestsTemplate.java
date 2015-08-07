package com.mycompany.pizzapp.repository.template;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repositoryContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class RepositoryTestsTemplate extends AbstractTransactionalJUnit4SpringContextTests{

}
