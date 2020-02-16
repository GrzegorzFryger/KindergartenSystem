package pl.edu.pja.prz.scheduler.facade.mapper;

import org.mapstruct.Mapper;
import pl.edu.pja.prz.scheduler.facade.dto.JobInfoDto;
import pl.edu.pja.prz.scheduler.facade.dto.ScheduleJobInfoDto;
import pl.edu.pja.prz.scheduler.model.JobInfo;
import pl.edu.pja.prz.scheduler.model.ScheduleJobInfo;

@Mapper(componentModel = "spring")
public interface SchedulerMapper {

	ScheduleJobInfo toScheduleJobInfo(ScheduleJobInfoDto scheduleJobInfoDto);

	ScheduleJobInfoDto fromScheduleJobInfo(ScheduleJobInfo scheduleJobInfo);

	JobInfo toJobInfo(JobInfoDto jobInfoDto);

	JobInfoDto fromJobInfo(JobInfo jobInfo);

}
