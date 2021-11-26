drop database if exists scgj_placement_portal;
create database scgj_placement_portal;
use scgj_placement_portal;

create table users(id int auto_increment primary key, mobile_number bigint(12) not null unique, otp varchar(10), username varchar(225), state varchar(255), pincode int(10), user_role enum('Admin','Employer','Candidate'),
is_active_flg enum('Y','N') not null default 'Y', enrolment_status enum('A','IR','R') not null default 'A', account_status_updated_on date, enrolment_updated_on date, registered_on date not null, last_login datetime);

create table employer(id int auto_increment primary key, user_id int, email_address varchar(255) unique, industry_type varchar(255), company_type varchar(255),
company_scale varchar(255), pan_number varchar(20), liasing_authority varchar(255), designation varchar(255), landline_number varchar(16), website varchar(200) default 'N/A', address VARCHAR(255), foreign key(user_id) references users(id));

create table company_scale(id int primary key auto_increment, company_scale varchar(255));

create table company_type(id int primary key auto_increment, company_type varchar(255));

create table states(id int primary key auto_increment, states varchar(255));

create table candidate_polling(id int auto_increment primary key, user_id int unique, poll_count int,polled_at datetime, foreign key (user_id) references users(id));

create table industry_type(id int primary key auto_increment, industry_type varchar(255));

create table education_qualification(id int auto_increment primary key, education_qualification varchar(255));

create table professional_experience(id int primary key auto_increment, experience varchar(255));

create table job_postings (id int auto_increment primary key, job_id varchar(255) unique not null, job_title varchar(255), total_vacancy int, min_salary int, max_salary int,
state varchar(255), district varchar(255), experience_required varchar(255), qualification_required varchar(255), job_role varchar(255), occupation varchar(255),
application_date date, job_summary text, job_description_path varchar(255), leave_policy text, monthly_incentives text, work_timings varchar(255),
contact_number bigint(12), interview_start_date_time datetime, interview_end_date_time datetime, walk_in_interview_flg enum('Y','N') default null, preferred_gender enum('M','F','O') default null,
army_background_preference enum('Y','N') default null, cgsc_certificate_preference enum('Y','N') default 'N', posted_by int ,created_at date, published_at date,job_status enum('Not Published','Published','Closed'), job_approval_status enum('In Review','Rejected','Approved'), approval_status_updated_on date ,active_flg enum('Y','N'),updated_at date,
foreign key (posted_by) references users(id));

create table job_roles(id int primary key auto_increment, job_role varchar(255), is_active_flg enum('Y','N') not null default 'Y');

create table occupation( id int primary key auto_increment, occupation_name varchar(255), job_role_id int, is_active_flg enum('Y','N'), foreign key (job_role_id) references job_roles(id)
);


create table job_candidate_mapping(id int primary key auto_increment, job_id int, candidate_id int, salary int, joining_date date, offer_letter_path varchar(255), applied_on date, status enum('In Review','Shortlisted','Rejected','Hired'), updated_on date ,foreign key(job_id) references job_postings(id), foreign key(candidate_id) references users(id));

create table training_partner ( id int primary key auto_increment,  tp_name varchar(255), is_active enum('Y','N') default 'Y');

create table candidates(id int primary key auto_increment, user_id int, guardian_mobile_number bigint(12), gender enum('M','F','O'), defence_background enum('Y','N'),qualification varchar(255), is_cgsc_certified enum('Y','N'), certificate_number varchar(100), working_experience varchar(255),resume_path varchar(255), certificate_path varchar(255), address VARCHAR(255), dob DATE, age INT, guardian_name VARCHAR(100),aadhaar_number BIGINT(12), job_role_id INT, tp_id int, foreign key (user_id) references users(id), foreign key(tp_id) references training_partner(id), foreign key(job_role_id) references job_roles(id));

create table job_postings_review_comments(id int primary key auto_increment, job_id int, admin_comment varchar(255), commented_by int, updated_on datetime, foreign key(job_id) references job_postings(id), foreign key(commented_by) references users(id));

create table notifications(id int primary key auto_increment, message text, sent_on DATETIME, job_role_id int, sent_by int, foreign key(job_role_id) references job_roles(id), foreign key(sent_by) references users(id));