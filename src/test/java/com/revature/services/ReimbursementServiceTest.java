package com.revature.services;

/*
public class ReimbursementServiceTest {

	private static ReimbursementService reimbursementService;
	private static ReimbursementDAO reimbursementDAO;
	
	private Reimbursement REIMBURSEMENT_TO_PROCESS;
	private Reimbursement GENERIC_REIMBURSEMENT_1;
	private Reimbursement GENERIC_REIMBURSEMENT_2;
	private List<Reimbursement> GENERIC_ALL_PENDING_REIMBURSEMENTS;
	private User GENERIC_EMPLOYEE_1;
	private User GENERIC_FINANCE_MANAGER_1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reimbursementService = new ReimbursementService();
		reimbursementDAO = mock(ReimbursementDAO.class);
	}
	
	@Before
	public void setUp() throws Exception {
		GENERIC_EMPLOYEE_1 = new User(1, "genericEmployee1", "genericPassword", Role.EMPLOYEE);
		GENERIC_FINANCE_MANAGER_1 = new User(1, "genericManager1", "genericPassword", Role.FINANCE_MANAGER);
		
		REIMBURSEMENT_TO_PROCESS = new Reimbursement(2, Status.PENDING, GENERIC_EMPLOYEE_1, null, 150.00);
		
		GENERIC_REIMBURSEMENT_1 = new Reimbursement(1, Status.PENDING, GENERIC_EMPLOYEE_1, null, 100.00);
		GENERIC_REIMBURSEMENT_2 = new Reimbursement(2, Status.APPROVED, GENERIC_EMPLOYEE_1, GENERIC_FINANCE_MANAGER_1, 150.00);
		
		GENERIC_ALL_PENDING_REIMBURSEMENTS = new ArrayList<Reimbursement>();
		GENERIC_ALL_PENDING_REIMBURSEMENTS.add(GENERIC_REIMBURSEMENT_1);
	}
	
	@Test
	public void testProcessPassesWhenUserIsFinanceManagerAndReimbursementExistsAndUpdateSuccessful() {
		when(reimbursementDAO.getById(anyInt())).thenReturn(Optional.of(GENERIC_REIMBURSEMENT_1));
		when(reimbursementDAO.update(any())).thenReturn(GENERIC_REIMBURSEMENT_2);
		
		assertEquals(GENERIC_REIMBURSEMENT_2, reimbursementService.process(REIMBURSEMENT_TO_PROCESS, Status.APPROVED, GENERIC_FINANCE_MANAGER_1));
		
		verify(reimbursementDAO).getById(REIMBURSEMENT_TO_PROCESS.getId());
		verify(reimbursementDAO).update(REIMBURSEMENT_TO_PROCESS);
	}
	
	@Test
	public void testGetReimbursementByStatusPassesWhenReimbursementsAreSuccessfullyReturned() {
		when(reimbursementDAO.getByStatus(any())).thenReturn(GENERIC_ALL_PENDING_REIMBURSEMENTS);
		
		assertEquals(GENERIC_ALL_PENDING_REIMBURSEMENTS, reimbursementService.getReimbursementsByStatus(Status.PENDING));
		
		verify(reimbursementDAO).getByStatus(Status.PENDING);
	}

}
*/