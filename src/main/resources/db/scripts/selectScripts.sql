SELECT  d.id,d.name,d.school_id
from departments t
         inner join school.departments d on t.id = d.id
where t.id=1;