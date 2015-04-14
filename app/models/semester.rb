# Model Semester
# Table semesters
class Semester < ActiveRecord::Base
  validates :name, presence: true, uniqueness: true
  validates :start_date, presence: true, uniqueness: true
  validates :end_date, presence: true, uniqueness: true
end
