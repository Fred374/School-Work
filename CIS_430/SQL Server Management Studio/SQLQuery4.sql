use company;

SELECT * FROM VDEPT_BUDGET;

select d.DNO, e.FNAME, e.LNAME, s.FNAME, s.LNAME
from (department d left outer join employee e 
	on e.DNO=d.DNO)
left outer join employee s
	on e.SUPERSSN = s.SSN
order by d.DNO, e.FNAME;

select e.DNO, e.FNAME, e.LNAME, s.FNAME, s.LNAME
from employee e left outer join employee s
	on e.SUPERSSN = s.SSN
order by e.DNO, e.FNAME;

select e.SSN, e.LNAME
from employee e, works_on w, dependents d
where e.SEX = 'F' and d.ESSN = e.SSN and d.relationship = 'Spouse'
group by e.SSN, e.LNAME
having count(w.essn) > 3;

select e.FNAME, e.LNAME
from employee e, dependents d, department n
where n.dname = 'Research' and n.dno = e.dno and e.ssn = d.essn and d.relationship = 'spouse'
and e.ssn not in (select d.essn
				from dependents d
				where d.relationship = 'son')
and e.ssn not in (select d.essn
				from dependents d
				where d.relationship = 'daughter');

select e.LNAME
from employee e
where e.ssn in (select d.essn
				from dependents d
				where d.relationship = 'daughter')
and e.ssn not in (select d.essn
				from dependents d
				where d.relationship = 'son')
and e.ssn in (select d.essn
				from dependents d
				where d.relationship = 'spouse');