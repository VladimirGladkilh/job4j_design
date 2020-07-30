--select accepted meets and count of users
select mm.name, count(m.user_id)
from meeting m
inner join meet mm on m.meet_id=mm.id
where m.state=true
group by mm.name;

--select meets without users
select mm.name
from meet mm
left join meeting m on m.meet_id=mm.id
where m.id is null;