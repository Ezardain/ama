# ActivityReports controller
class ActivityReportsController < ApplicationController
  before_action :authenticate_user!

  def index
    @activity_reports = ActivityReport.all
  end

  def new
    @activity_report = ActivityReport.new
  end

  def create
    @activity_report = ActivityReport.new(activity_report_params)
    if @activity_report.save
      flash[:notice] = 'Grupo creado con éxito'
      redirect_to activity_reports_path
    else
      render 'new'
    end
  end

  def edit
    @activity_report = ActivityReport.find(params[:id])
  end

  def update
    @activity_report = ActivityReport.find(params[:id])
    if @activity_report.update_attributes(activity_report_params)
      flash[:success] = 'ActivityReport updated successfully'
      redirect_to activity_reports_path
    else
      render 'edit'
    end
  end

  private

  def activity_report_params
    params.require(:activity_report).permit(:user_id, :activity_instance_id,
                                            :comentary, :hour_estimate)
  end
end
