# Semester controller
class SemestersController < ApplicationController
  before_action :authenticate_user!

  def index
    @semesters = Semester.all
    authorize Semester
  end

  def new
    @semester = Semester.new
    authorize @semester
  end

  def create
    @semester = Semester.new(semester_params)
    authorize @semester
    if @semester.save
      flash[:success] = 'Semestre creado con éxito'
      redirect_to semesters_path
    else
      render 'new'
    end
  end

  def edit
    @semester = Semester.find(params[:id])
    authorize @user
  end

  def update
    @semester = Semester.find(params[:id])
    authorize @semester
    if @semester.update_attributes(semester_params)
      flash[:success] = 'Semester updated successfully'
      redirect_to semesters_path
    else
      render 'edit'
    end
  end

  private

  def semester_params
    params.require(:semester)
      .permit(:name, :start_date, :end_date,
              activity_instances_attributes: [:activity_id, :hours, :_destroy])
  end
end
